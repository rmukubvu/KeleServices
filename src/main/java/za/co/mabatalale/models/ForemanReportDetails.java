package za.co.mabatalale.models;

import javax.persistence.*;

/**
 * Created by robson on 2017/03/02.
 */
@Entity
@Table(name = "foreman_report_details")
public class ForemanReportDetails {

    private Integer detailsId;
    private String operator;
    private String plantNumber;
    /* trading hours */
    private String tradingOpenHours;
    private String tradingcClosingHours;
    private String tradingTotalHours;
    /* machine hours */
    private String machineOpenHours;
    private String machineClosingHours;
    private String machineTotalHours;
    /* holes */
    private String newHoles;
    private String newMetersDrilled;
    /* re-drill */
    private String redrillHoles;
    private String redrillMetersDrilled;
    private String mhr;
    private String benchStartNumber;
    private String benchEndNumber;
    private String assistant;
    private int rigId;
    private int dateInt;
    private int headerIdRef;

    @Basic
    @Column(name = "rig_int")
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
    @Column(name = "operator")
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Basic
    @Column(name = "plant_number")
    public String getPlantNumber() {
        return plantNumber;
    }

    public void setPlantNumber(String plantNumber) {
        this.plantNumber = plantNumber;
    }

    @Basic
    @Column(name = "trading_open_hours")
    public String getTradingOpenHours() {
        return tradingOpenHours;
    }

    public void setTradingOpenHours(String tradingOpenHours) {
        this.tradingOpenHours = tradingOpenHours;
    }

    @Basic
    @Column(name = "trading_close_hours")
    public String getTradingcClosingHours() {
        return tradingcClosingHours;
    }

    public void setTradingcClosingHours(String tradingcClosingHours) {
        this.tradingcClosingHours = tradingcClosingHours;
    }

    @Basic
    @Column(name = "trading_total_hours")
    public String getTradingTotalHours() {
        return tradingTotalHours;
    }

    public void setTradingTotalHours(String tradingTotalHours) {
        this.tradingTotalHours = tradingTotalHours;
    }

    @Basic
    @Column(name = "machine_open_hours")
    public String getMachineOpenHours() {
        return machineOpenHours;
    }

    public void setMachineOpenHours(String machineOpenHours) {
        this.machineOpenHours = machineOpenHours;
    }

    @Basic
    @Column(name = "machine_close_hours")
    public String getMachineClosingHours() {
        return machineClosingHours;
    }

    public void setMachineClosingHours(String machineClosingHours) {
        this.machineClosingHours = machineClosingHours;
    }

    @Basic
    @Column(name = "machine_total_hours")
    public String getMachineTotalHours() {
        return machineTotalHours;
    }

    public void setMachineTotalHours(String machineTotalHours) {
        this.machineTotalHours = machineTotalHours;
    }

    @Basic
    @Column(name = "new_holes")
    public String getNewHoles() {
        return newHoles;
    }

    public void setNewHoles(String newHoles) {
        this.newHoles = newHoles;
    }

    @Basic
    @Column(name = "new_meters_drilled")
    public String getNewMetersDrilled() {
        return newMetersDrilled;
    }

    public void setNewMetersDrilled(String newMetersDrilled) {
        this.newMetersDrilled = newMetersDrilled;
    }

    @Basic
    @Column(name = "re_drill_holes")
    public String getRedrillHoles() {
        return redrillHoles;
    }

    public void setRedrillHoles(String redrillHoles) {
        this.redrillHoles = redrillHoles;
    }

    @Basic
    @Column(name = "re_drill_meters")
    public String getRedrillMetersDrilled() {
        return redrillMetersDrilled;
    }

    public void setRedrillMetersDrilled(String redrillMetersDrilled) {
        this.redrillMetersDrilled = redrillMetersDrilled;
    }

    @Basic
    @Column(name = "mhr")
    public String getMhr() {
        return mhr;
    }

    public void setMhr(String mhr) {
        this.mhr = mhr;
    }

    @Basic
    @Column(name = "bench_start_number")
    public String getBenchStartNumber() {
        return benchStartNumber;
    }

    public void setBenchStartNumber(String benchStartNumber) {
        this.benchStartNumber = benchStartNumber;
    }

    @Basic
    @Column(name = "bench_end_number")
    public String getBenchEndNumber() {
        return benchEndNumber;
    }

    public void setBenchEndNumber(String benchEndNumber) {
        this.benchEndNumber = benchEndNumber;
    }

    @Basic
    @Column(name = "assistant")
    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    @Id
    @Column(name = "foreman_report_details_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(Integer detailsId) {
        this.detailsId = detailsId;
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
