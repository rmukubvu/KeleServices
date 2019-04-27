package za.co.mabatalale.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "back_date_logs", schema = "basil", catalog = "")
public class BackDateLogs {
    private int backDateLogsId;
    private String tableName;
    private Long tablePrimaryKey;
    private Timestamp startTime;
    private Timestamp endTime;
    private Timestamp createdDate;
    private Integer dateInt;
    private Integer userId;
    private Timestamp alterDate;

    @Id
    @Column(name = "back_date_logs_id")
    public int getBackDateLogsId() {
        return backDateLogsId;
    }

    public void setBackDateLogsId(int backDateLogsId) {
        this.backDateLogsId = backDateLogsId;
    }

    @Basic
    @Column(name = "table_name")
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Basic
    @Column(name = "table_primary_key")
    public Long getTablePrimaryKey() {
        return tablePrimaryKey;
    }

    public void setTablePrimaryKey(Long tablePrimaryKey) {
        this.tablePrimaryKey = tablePrimaryKey;
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

    @Basic
    @Column(name = "date_int")
    public Integer getDateInt() {
        return dateInt;
    }

    public void setDateInt(Integer dateInt) {
        this.dateInt = dateInt;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "alter_date")
    public Timestamp getAlterDate() {
        return alterDate;
    }

    public void setAlterDate(Timestamp alterDate) {
        this.alterDate = alterDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BackDateLogs that = (BackDateLogs) o;
        return backDateLogsId == that.backDateLogsId &&
                Objects.equals(tableName, that.tableName) &&
                Objects.equals(tablePrimaryKey, that.tablePrimaryKey) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(dateInt, that.dateInt) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(alterDate, that.alterDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(backDateLogsId, tableName, tablePrimaryKey, startTime, endTime, createdDate, dateInt, userId, alterDate);
    }
}
