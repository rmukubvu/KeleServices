package za.co.mabatalale.models;

import za.co.mabatalale.utils.DateUtil;

/**
 * Created by robson on 2017/03/04.
 */
public class DisplayTimes {

    private long totalTime;

    public DisplayTimes(long totalTime){
        this.totalTime = totalTime;
    }

    public String getTotalTime() {
        return DateUtil.getFormattedTime(totalTime);
    }

}
