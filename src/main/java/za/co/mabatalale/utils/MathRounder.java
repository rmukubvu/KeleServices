package za.co.mabatalale.utils;

import java.text.DecimalFormat;

/**
 * Created by robson on 2017/05/31.
 */
public class MathRounder {

    public static String roundTo2Decimals(double val) {
        DecimalFormat df2 = new DecimalFormat("###,###,##0.00");
        return df2.format(val);
    }
}
