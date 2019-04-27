package za.co.mabatalale.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by robson on 2017/02/27.
 */
@Entity
@Table(name = "basil_users_leave", schema = "basil", catalog = "")
public class BasilUsersLeave {
    private int basilUsersLeaveId;
    private Integer leaveTypeId;
    private Integer userId;
    private Timestamp startDate;
    private Timestamp endDate;
    private Integer approvedBy;
    private Timestamp createdDate;
    private int dateInt;

    @Id
    @Column(name = "basil_users_leave_id")
    public int getBasilUsersLeaveId() {
        return basilUsersLeaveId;
    }

    public void setBasilUsersLeaveId(int basilUsersLeaveId) {
        this.basilUsersLeaveId = basilUsersLeaveId;
    }

    @Basic
    @Column(name = "leave_type_id")
    public Integer getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(Integer leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
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
    @Column(name = "start_date")
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date")
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "approved_by")
    public Integer getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Integer approvedBy) {
        this.approvedBy = approvedBy;
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
    public int getDateInt() {
        return dateInt;
    }

    public void setDateInt(int dateInt) {
        this.dateInt = dateInt;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasilUsersLeave that = (BasilUsersLeave) o;

        if (basilUsersLeaveId != that.basilUsersLeaveId) return false;
        if (leaveTypeId != null ? !leaveTypeId.equals(that.leaveTypeId) : that.leaveTypeId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (approvedBy != null ? !approvedBy.equals(that.approvedBy) : that.approvedBy != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = basilUsersLeaveId;
        result = 31 * result + (leaveTypeId != null ? leaveTypeId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (approvedBy != null ? approvedBy.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
