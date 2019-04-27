package za.co.mabatalale.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by robson on 2017/02/27.
 */
@Entity
@Table(name = "standing_logs", schema = "basil", catalog = "")
public class StandingLogs {
    private int standingLogsId;
    private Integer operatorSheetId;
    private Integer standingTypeId;
    private Timestamp startTime;
    private Timestamp endTime;
    private Timestamp createdDate;
    private Integer dateInt;
    private Integer rigId;
    private String standingReason;

    @Id
    @Column(name = "standing_logs_id")
    public int getStandingLogsId() {
        return standingLogsId;
    }

    public void setStandingLogsId(int standingLogsId) {
        this.standingLogsId = standingLogsId;
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
    @Column(name = "standing_type_id")
    public Integer getStandingTypeId() {
        return standingTypeId;
    }

    public void setStandingTypeId(Integer standingTypeId) {
        this.standingTypeId = standingTypeId;
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

        StandingLogs that = (StandingLogs) o;

        if (standingLogsId != that.standingLogsId) return false;
        if (operatorSheetId != null ? !operatorSheetId.equals(that.operatorSheetId) : that.operatorSheetId != null)
            return false;
        if (standingTypeId != null ? !standingTypeId.equals(that.standingTypeId) : that.standingTypeId != null)
            return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = standingLogsId;
        result = 31 * result + (operatorSheetId != null ? operatorSheetId.hashCode() : 0);
        result = 31 * result + (standingTypeId != null ? standingTypeId.hashCode() : 0);
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
    @Column(name = "standing_reason")
    public String getStandingReason() {
        return standingReason;
    }

    public void setStandingReason(String standingReason) {
        this.standingReason = standingReason;
    }
}
