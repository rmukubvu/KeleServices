package za.co.mabatalale.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by robson on 2017/02/27.
 */
@Entity
@Table(name = "breakdown_logs", schema = "basil", catalog = "")
public class BreakdownLogs {
    private int breakdownLogsId;
    private Integer operatorSheetId;
    private Integer breakdownTypeId;
    private Integer lookUpDataId;
    private String optionalText;
    private Timestamp startTime;
    private Timestamp endTime;
    private Timestamp createdDate;
    private Integer dateInt;
    private Integer rigId;
    private String breakdownText;

    @Id
    @Column(name = "breakdown_logs_id")
    public int getBreakdownLogsId() {
        return breakdownLogsId;
    }

    public void setBreakdownLogsId(int breakdownLogsId) {
        this.breakdownLogsId = breakdownLogsId;
    }

    @Basic
    @Column(name = "operator_sheet_id")
    public Integer getOperatorSheetId() {
        return operatorSheetId;
    }

    public void setOperatorSheetId(Integer operatorSheetId) {
        this.operatorSheetId = operatorSheetId;
    }

    @Basic
    @Column(name = "breakdown_type_id")
    public Integer getBreakdownTypeId() {
        return breakdownTypeId;
    }

    public void setBreakdownTypeId(Integer breakdownTypeId) {
        this.breakdownTypeId = breakdownTypeId;
    }

    @Basic
    @Column(name = "look_up_data_id")
    public Integer getLookUpDataId() {
        return lookUpDataId;
    }

    public void setLookUpDataId(Integer lookUpDataId) {
        this.lookUpDataId = lookUpDataId;
    }

    @Basic
    @Column(name = "optional_text")
    public String getOptionalText() {
        return optionalText;
    }

    public void setOptionalText(String optionalText) {
        this.optionalText = optionalText;
    }

    @Basic
    @Column(name = "start_time")
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BreakdownLogs that = (BreakdownLogs) o;

        if (breakdownLogsId != that.breakdownLogsId) return false;
        if (operatorSheetId != null ? !operatorSheetId.equals(that.operatorSheetId) : that.operatorSheetId != null)
            return false;
        if (breakdownTypeId != null ? !breakdownTypeId.equals(that.breakdownTypeId) : that.breakdownTypeId != null)
            return false;
        if (lookUpDataId != null ? !lookUpDataId.equals(that.lookUpDataId) : that.lookUpDataId != null) return false;
        if (optionalText != null ? !optionalText.equals(that.optionalText) : that.optionalText != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = breakdownLogsId;
        result = 31 * result + (operatorSheetId != null ? operatorSheetId.hashCode() : 0);
        result = 31 * result + (breakdownTypeId != null ? breakdownTypeId.hashCode() : 0);
        result = 31 * result + (lookUpDataId != null ? lookUpDataId.hashCode() : 0);
        result = 31 * result + (optionalText != null ? optionalText.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "date_int")
    public Integer getDateInt() {
        return dateInt;
    }

    public void setDateInt(Integer dateInt) {
        this.dateInt = dateInt;
    }

    @Basic
    @Column(name = "rig_id")
    public Integer getRigId() {
        return rigId;
    }

    public void setRigId(Integer rigId) {
        this.rigId = rigId;
    }

    @Basic
    @Column(name = "breakdown_text")
    public String getBreakdownText() {
        return breakdownText;
    }

    public void setBreakdownText(String breakdownText) {
        this.breakdownText = breakdownText;
    }
}
