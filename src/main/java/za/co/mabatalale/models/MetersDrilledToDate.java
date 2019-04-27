package za.co.mabatalale.models;

import javax.persistence.*;

/**
 * Created by robson on 2017/04/09.
 */
@Entity
@Table(name = "meters_drilled_graph", schema = "basil_prod", catalog = "")
public class MetersDrilledToDate {

    private int drilledId;
    private String day;
    private double meters;
    private double cumMeters;
    private int dateInt;
    private int period;

    @Basic
    @Column(name = "day")
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Basic
    @Column(name = "meters")
    public double getMeters() {
        return meters;
    }

    public void setMeters(double meters) {
        this.meters = meters;
    }

    @Basic
    @Column(name = "date_int")
    public int getDateInt() {
        return dateInt;
    }

    public void setDateInt(int dateInt) {
        this.dateInt = dateInt;
    }

    @Id
    @Column(name = "meters_drilled_graph_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getDrilledId() {
        return drilledId;
    }

    public void setDrilledId(int drilledId) {
        this.drilledId = drilledId;
    }

    @Basic
    @Column(name = "cum_meters")
    public double getCumMeters() {
        return cumMeters;
    }

    public void setCumMeters(double cumMeters) {
        this.cumMeters = cumMeters;
    }

    @Basic
    @Column(name = "period")
    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
