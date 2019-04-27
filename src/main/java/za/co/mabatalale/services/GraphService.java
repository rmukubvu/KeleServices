package za.co.mabatalale.services;

import za.co.mabatalale.entities.ProductionRecord;
import za.co.mabatalale.models.ActualDepthPerHour;
import za.co.mabatalale.models.MeterDrilledVsTarget;
import za.co.mabatalale.models.MetersDrilledToDate;
import za.co.mabatalale.repos.MeterTargetRepository;
import za.co.mabatalale.repos.MetersDrilledToDateRepository;
import za.co.mabatalale.repos.ProductionEntryRepository;
import za.co.mabatalale.utils.DateUtil;
import za.co.mabatalale.utils.MapUtil;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by robson on 2017/04/09.
 */
public class GraphService {

    private final MeterTargetRepository meterTargetRepository;
    private final MetersDrilledToDateRepository metersDrilledToDateRepository;
    private ProductionCaptureService productionCaptureService;

    @Inject
    public GraphService(MeterTargetRepository meterTargetRepository,MetersDrilledToDateRepository metersDrilledToDateRepository) {
        this.meterTargetRepository = meterTargetRepository;
        this.metersDrilledToDateRepository = metersDrilledToDateRepository;
        this.productionCaptureService = new ProductionCaptureService(new ProductionEntryRepository());
    }

    public List<MeterDrilledVsTarget> getMetersDrilledVsTarget(){
        return meterTargetRepository.findByPeriod(DateUtil.getCurrentPeriod());
    }

    public List<MetersDrilledToDate> getMetersDrilledToDate(){
        return metersDrilledToDateRepository.findByPeriod(DateUtil.getCurrentPeriod());
    }

    public List<ActualDepthPerHour> getActualDepthPerHour(){
        List<ActualDepthPerHour> actualDepthPerHourList = new ArrayList<>();
        List<ProductionRecord> productionRecords = productionCaptureService.findByDateInt(DateUtil.getCurrentDay());
        Map<Integer, BigDecimal> map = new TreeMap<>();
        for (ProductionRecord record :
                productionRecords) {

            Integer hour = DateUtil.getHourFromTimeStamp(record.getCreatedDate().getTime());
            if (map.get(hour) != null) {
                map.put(hour, map.get(hour).add(record.getMeters()));
            } else {
                map.put(hour, record.getMeters());
            }
        }

        final double[] previousValue = {0};
        map.forEach((k, v) -> {
            ActualDepthPerHour model = new ActualDepthPerHour();
            model.setHour(k);
            previousValue[0] +=  v.doubleValue();
            model.setCumulativeDepth(previousValue[0]);
            model.setActualDepth(v.doubleValue());
            actualDepthPerHourList.add(model);
        });

        return actualDepthPerHourList;
    }
}
