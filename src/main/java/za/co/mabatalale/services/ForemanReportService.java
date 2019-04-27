package za.co.mabatalale.services;

import za.co.mabatalale.models.ForemanReportDetails;
import za.co.mabatalale.models.ForemanReportFooter;
import za.co.mabatalale.models.ForemanReportHeader;
import za.co.mabatalale.models.ForemanReportSummary;
import za.co.mabatalale.repos.ForemanDetailsRepository;
import za.co.mabatalale.repos.ForemanFooterRepository;
import za.co.mabatalale.repos.ForemanHeaderRepository;
import za.co.mabatalale.repos.ForemanSummaryRepository;
import za.co.mabatalale.utils.DateUtil;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Created by robson on 2017/03/04.
 */
public class ForemanReportService {

    private final ForemanHeaderRepository foremanHeaderRepository;
    private final ForemanDetailsRepository foremanDetailsRepository;
    private final ForemanSummaryRepository foremanSummaryRepository;
    private final ForemanFooterRepository foremanFooterRepository;

    @Inject
    public ForemanReportService(ForemanHeaderRepository foremanHeaderRepository,
                                ForemanDetailsRepository foremanDetailsRepository,
                                ForemanSummaryRepository foremanSummaryRepository,
                                ForemanFooterRepository foremanFooterRepository){
        this.foremanHeaderRepository = foremanHeaderRepository;
        this.foremanDetailsRepository = foremanDetailsRepository;
        this.foremanSummaryRepository = foremanSummaryRepository;
        this.foremanFooterRepository = foremanFooterRepository;
    }

    /* to save */

    public void saveForemanReportHeader(List<ForemanReportHeader> foremanReportHeaders){
        for (ForemanReportHeader header : foremanReportHeaders
             ) {
            foremanHeaderRepository.save(header);
        }
    }

    public void saveForemanReportDetails(List<ForemanReportDetails> records){
        for (ForemanReportDetails model : records
                ) {
            foremanDetailsRepository.save(model);
        }
    }

    public void saveForemanReportSummary(List<ForemanReportSummary> records){
        for (ForemanReportSummary model : records
                ) {
            foremanSummaryRepository.save(model);
        }
    }

    public void saveForemanReportFooter(List<ForemanReportFooter> records){
        for (ForemanReportFooter model : records
                ) {
            foremanFooterRepository.save(model);
        }
    }

    /* to display */

    public ForemanReportHeader getReportHeader(int date,String shift,String crew){
        List<ForemanReportHeader> headers = foremanHeaderRepository.findByDateIntAndShift(date,shift);
        Optional<ForemanReportHeader> singleRecord = headers
                .stream()
                .filter(p-> p.getCrew().equalsIgnoreCase(crew))
                .findFirst();
        if (singleRecord.isPresent())
            return singleRecord.get();
        return new ForemanReportHeader();
    }

    public List<ForemanReportSummary> getReportSummary(int refId){
        return foremanSummaryRepository.findByHeaderIdRef(refId);
    }

    public List<ForemanReportFooter> getReportFooter(int refId){
        return foremanFooterRepository.findByHeaderIdRef(refId);
    }

    public List<ForemanReportDetails> getReportDetails(int refId){
        return foremanDetailsRepository.findByHeaderIdRef(refId);
    }

    public List<ForemanReportDetails> getReportDetailsByDate(int date){
        return foremanDetailsRepository.findByDateInt(date);
    }

    //get entire report
    public List<ForemanReportSummary> getReportSummaryForPreviousDay(){
        return foremanSummaryRepository.findByDateInt(DateUtil.getPreviousDay());
    }

    public List<ForemanReportFooter> getReportFooterForPreviousDay(){
        return foremanFooterRepository.findByDateInt(DateUtil.getPreviousDay());
    }

    public List<ForemanReportDetails> getReportDetailsForPreviousDay(){
        return foremanDetailsRepository.findByDateInt(DateUtil.getPreviousDay());
    }
}
