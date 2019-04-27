package za.co.mabatalale.models;

import javax.persistence.*;

/**
 * Created by robson on 2017/03/02.
 */
@Entity
@Table(name = "foreman_report_footer")
public class ForemanReportFooter {

    private Integer footerId;
    private String plantNumber;
    private String to;
    private String from;
    private String durationHours;
    private String delayType;
    private String delayDescription;
    private int rigId;
    private int dateInt;
    private int headerIdRef;

    @Basic
    @Column(name = "plant_number")
    public String getPlantNumber() {
        return plantNumber;
    }

    public void setPlantNumber(String plantNumber) {
        this.plantNumber = plantNumber;
    }

    @Basic
    @Column(name = "end_time")
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }


    @Basic
    @Column(name = "start_time")
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }


    @Basic
    @Column(name = "duration_hours")
    public String getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(String durationHours) {
        this.durationHours = durationHours;
    }

    @Basic
    @Column(name = "delay_type")
    public String getDelayType() {
        return delayType;
    }

    public void setDelayType(String delayType) {
        this.delayType = delayType;
    }

    @Basic
    @Column(name = "delay_desc")
    public String getDelayDescription() {
        return delayDescription;
    }

    public void setDelayDescription(String delayDescription) {
        this.delayDescription = delayDescription;
    }

    @Id
    @Column(name="foreman_report_footer_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getFooterId() {
        return footerId;
    }

    public void setFooterId(Integer footerId) {
        this.footerId = footerId;
    }

    @Basic
    @Column(name = "rig_id")
    public int getRigId() {
        return rigId;
    }

    public void setRigId(int rigId) {
        this.rigId = rigId;
    }

    @Basic
    @Column(name = "date_int")
    public int getDateInt() {
        return dateInt;
    }

    public void setDateInt(int dateInt) {
        this.dateInt = dateInt;
    }

    @Basic
    @Column(name="header_id_ref")
    public int getHeaderIdRef() {
        return headerIdRef;
    }

    public void setHeaderIdRef(int headerIdRef) {
        this.headerIdRef = headerIdRef;
    }
}
