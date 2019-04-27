package za.co.mabatalale.services;

import za.co.mabatalale.models.DelayAnalysisReportModel;
import za.co.mabatalale.repos.DelayAnalysisRepository;
import za.co.mabatalale.utils.DateUtil;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by robson on 2017/03/03.
 */
public class DelayAnalysisService {

    private final DelayAnalysisRepository delayAnalysisRepository;

    @Inject
    public DelayAnalysisService(DelayAnalysisRepository delayAnalysisRepository){
        this.delayAnalysisRepository = delayAnalysisRepository;
    }

    public List<DelayAnalysisReportModel> getDelayReportForDate(int date){
        return delayAnalysisRepository.findByDateInt(date);
    }

    public List<DelayAnalysisReportModel> getDelayReportBetweenRange(int date1,int date2) throws Exception {
        if (!DateUtil.checkDatesBetween(date1,date2))
            throw new Exception("End date cannot be less than start date");
        return delayAnalysisRepository.findByDateIntBetween(date1,date2);
    }
}
