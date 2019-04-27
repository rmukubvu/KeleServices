package za.co.mabatalale.models;

import javax.persistence.*;

/**
 * Created by robson on 2017/03/03.
 */

@Entity
@Table(name="foreman_report_summary")
public class ForemanReportSummary {
    private Integer foremanSummaryId;
    private int rigId;
    private int dateInt;
    private String totalTrammingHours;
    private String totalMachineHours;
    private String totalNewMeters;
    private String totatRedrillMeters;
    private String totalProductionMeters;
    private String totalMhr;
    private int headerIdRef;

    @Basic
    @Column(name = "total_tramming_hours")
    public String getTotalTrammingHours() {
        return totalTrammingHours;
    }

    public void setTotalTrammingHours(String totalTrammingHours) {
        this.totalTrammingHours = totalTrammingHours;
    }

    @Basic
    @Column(name = "total_machine_hours")
    public String getTotalMachineHours() {
        return totalMachineHours;
    }

    public void setTotalMachineHours(String totalMachineHours) {
        this.totalMachineHours = totalMachineHours;
    }

    @Basic
    @Column(name = "total_new_meters")
    public String getTotalNewMeters() {
        return totalNewMeters;
    }

    public void setTotalNewMeters(String totalNewMeters) {
        this.totalNewMeters = totalNewMeters;
    }

    @Basic
    @Column(name = "total_redrill_meters")
    public String getTotatRedrillMeters() {
        return totatRedrillMeters;
    }

    public void setTotatRedrillMeters(String totatRedrillMeters) {
        this.totatRedrillMeters = totatRedrillMeters;
    }

    @Basic
    @Column(name = "total_production_meter")
    public String getTotalProductionMeters() {
        return totalProductionMeters;
    }

    public void setTotalProductionMeters(String totalProductionMeters) {
        this.totalProductionMeters = totalProductionMeters;
    }

    @Basic
    @Column(name = "total_mhr")
    public String getTotalMhr() {
        return totalMhr;
    }

    public void setTotalMhr(String totalMhr) {
        this.totalMhr = totalMhr;
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

    @Id
    @Column(name = "foreman_report_summary_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getForemanSummaryId() {
        return foremanSummaryId;
    }

    public void setForemanSummaryId(Integer foremanSummaryId) {
        this.foremanSummaryId = foremanSummaryId;
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
