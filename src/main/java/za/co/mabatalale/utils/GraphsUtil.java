package za.co.mabatalale.utils;

import za.co.mabatalale.entities.ProductionRecord;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by robson on 2017/04/07.
 */
public class GraphsUtil {

    public static final String BREAKDOWN_COLOR_RED = "#ff0000";
    public static final String STANDING_COLOR_BLUE = "#0000ff";
    public static final String PRODUCTION_COLOR_GREEN = "#008000";
    public static final String SHIFTS_COLOR_GREEN = "#008000";
    //meters drilled vs target - this is cumulative ( per month )
    //actual depth drilled per hour
    //total meter drilled per month
    public static String getMetersDrilledPerHour(List<ProductionRecord> productionRecords,int operatorId,double totalMachineHours){
        BigDecimal meters = new BigDecimal(0);
        for (ProductionRecord record : productionRecords
                ) {
            if ( record.getOperatorId() == operatorId ){
                meters = meters.add(record.getMeters());
            }
        }
        if (totalMachineHours > 0)
            return String.valueOf((meters.divide(new BigDecimal(totalMachineHours),2,BigDecimal.ROUND_UP)));
        return "0.0";
    }

}
