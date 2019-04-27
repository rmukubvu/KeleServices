package za.co.mabatalale.graphs;

import za.co.mabatalale.entities.ProductionRecord;
import za.co.mabatalale.models.MetersDrilledToDate;
import za.co.mabatalale.repos.MetersDrilledToDateRepository;
import za.co.mabatalale.repos.ProductionEntryRepository;
import za.co.mabatalale.services.ProductionCaptureService;
import za.co.mabatalale.utils.DateUtil;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by robson on 2017/04/09.
 */
public class MetersDrilledToDateCummulativeJob {

    private ProductionCaptureService productionCaptureService;
    private MetersDrilledToDateRepository metersDrilledToDateRepository;

    public MetersDrilledToDateCummulativeJob(){
        this.productionCaptureService = new ProductionCaptureService(new ProductionEntryRepository());
        metersDrilledToDateRepository = new MetersDrilledToDateRepository();
    }

    public void runReportBuilder(){
        MetersDrilledToDate model = metersDrilledToDate(DateUtil.getPreviousDay());
        metersDrilledToDateRepository.save(model);
    }

    private MetersDrilledToDate metersDrilledToDate(int date){
        List<ProductionRecord> productionRecords = productionCaptureService.findByDateInt(date);
        BigDecimal metersDrilled = new BigDecimal(0);
        for (ProductionRecord record : productionRecords
                ) {
            metersDrilled = metersDrilled.add(record.getMeters());
        }
        return getPreviousData(date,metersDrilled);
    }

    private MetersDrilledToDate getPreviousData(int date,BigDecimal currentMeters){
        MetersDrilledToDate previous = this.metersDrilledToDateRepository.findByDateInt(DateUtil.getPreviousPreviousDay());
        MetersDrilledToDate record = new MetersDrilledToDate();
        if (previous == null){
            previous = new MetersDrilledToDate();
            previous.setCumMeters(0);

        }
        record.setDateInt(date);
        record.setDay(DateUtil.getPreviousDayGraphDateString());
        record.setMeters(currentMeters.doubleValue());
        record.setCumMeters(previous.getCumMeters() + currentMeters.doubleValue());
        record.setPeriod(DateUtil.getCurrentPeriod());
        return record;
    }
}
