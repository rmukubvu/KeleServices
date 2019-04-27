package za.co.mabatalale.graphs;

import za.co.mabatalale.entities.ProductionRecord;
import za.co.mabatalale.models.MeterDrilledVsTarget;
import za.co.mabatalale.repos.MeterTargetRepository;
import za.co.mabatalale.repos.ProductionEntryRepository;
import za.co.mabatalale.services.ProductionCaptureService;
import za.co.mabatalale.utils.DateUtil;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by robson on 2017/04/09.
 */
public class MetersDrilledVsTargetJob {

    private ProductionCaptureService productionCaptureService;
    private MeterTargetRepository meterTargetRepository;

    public MetersDrilledVsTargetJob() {
        this.productionCaptureService = new ProductionCaptureService(new ProductionEntryRepository());
        this.meterTargetRepository = new MeterTargetRepository();
    }

    public void runReportBuilder(){
        MeterDrilledVsTarget model = meterDrilledVsTarget(DateUtil.getPreviousDay());
        meterTargetRepository.save(model);
    }

    private MeterDrilledVsTarget meterDrilledVsTarget(int date){
        List<ProductionRecord> productionRecords = productionCaptureService.findByDateInt(date);
        BigDecimal metersDrilled = new BigDecimal(0);
        BigDecimal target = new BigDecimal(0);

        for (ProductionRecord record : productionRecords
             ) {
            metersDrilled  = metersDrilled.add(record.getMeters());
            target  = target.add(BigDecimal.valueOf(Double.parseDouble(record.getHoleDepthRequired())));
        }
        return getPreviousData(date,metersDrilled,target);
    }

    private MeterDrilledVsTarget getPreviousData(int date,BigDecimal currentMeters , BigDecimal currentTarget){
        MeterDrilledVsTarget previous =  this.meterTargetRepository.findByDateInt(DateUtil.getPreviousPreviousDay());
        MeterDrilledVsTarget record = new MeterDrilledVsTarget();
        if (previous == null){
            previous = new MeterDrilledVsTarget();
            previous.setCumMeters(0);
            previous.setTarget(0);
        }
        record.setDateInt(date);
        record.setDay(DateUtil.getPreviousDayGraphDateString());
        record.setMeters(currentMeters.doubleValue());
        record.setCumMeters(previous.getCumMeters() + currentMeters.doubleValue());
        record.setTarget(previous.getTarget() + currentTarget.doubleValue());
        record.setPeriod(DateUtil.getCurrentPeriod());
        return record;
    }

}
