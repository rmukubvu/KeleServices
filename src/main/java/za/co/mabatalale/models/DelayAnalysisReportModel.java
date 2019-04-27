package za.co.mabatalale.models;

import javax.persistence.*;

/**
 * Created by robson on 2017/03/02.
 */

@Entity
@Table(name ="delay_analysis_report")
public class DelayAnalysisReportModel {

    private String delayDescription;
    private int totalDelay;
    private Integer delayId;
    private int dateInt;
    private int rigId;
    private String sourceDelay;
    private String displayColor;


    @Id
    @Column(name = "delay_analysis_report_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getDelayId() {
        return delayId;
    }

    public void setDelayId(Integer delayId) {
        this.delayId = delayId;
    }

    @Basic
    @Column(name = "delay_reason")
    public String getDelayDescription() {
        return delayDescription;
    }

    public void setDelayDescription(String delayDescription) {
        this.delayDescription = delayDescription;
    }

    @Basic
    @Column(name = "number_of_times")
    public int getTotalDelay() {
        return totalDelay;
    }

    public void setTotalDelay(int totalDelay) {
        this.totalDelay = totalDelay;
    }


    @Basic
    @Column(name="date_int")
    public int getDateInt() {
        return dateInt;
    }

    public void setDateInt(int dateInt) {
        this.dateInt = dateInt;
    }

    @Basic
    @Column(name="rig_id")
    public int getRigId() {
        return rigId;
    }

    public void setRigId(int rigId) {
        this.rigId = rigId;
    }

    @Basic
    @Column(name="delay_source")
    public String getSourceDelay() {
        return sourceDelay;
    }

    public void setSourceDelay(String sourceDelay) {
        this.sourceDelay = sourceDelay;
    }

    @Basic
    @Column(name="display_color")
    public String getDisplayColor() {
        return displayColor;
    }

    public void setDisplayColor(String displayColor) {
        this.displayColor = displayColor;
    }
}
