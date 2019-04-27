package za.co.mabatalale.models;

/**
 * Created by robson on 2017/05/06.
 */
public class ActualDepthPerHour {
    private double actualDepth;
    private double cumulativeDepth;
    private int hour;

    public double getActualDepth() {
        return actualDepth;
    }

    public void setActualDepth(double actualDepth) {
        this.actualDepth = actualDepth;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public double getCumulativeDepth() {
        return cumulativeDepth;
    }

    public void setCumulativeDepth(double cumulativeDepth) {
        this.cumulativeDepth = cumulativeDepth;
    }
}
