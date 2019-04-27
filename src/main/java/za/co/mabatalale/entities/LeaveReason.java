package za.co.mabatalale.entities;

import javax.persistence.*;

/**
 * Created by robson on 2017/02/27.
 */
@Entity
@Table(name = "leave_reason", schema = "basil", catalog = "")
public class LeaveReason {
    private int leaveReasonId;
    private String reason;

    @Id
    @Column(name = "leave_reason_id")
    public int getLeaveReasonId() {
        return leaveReasonId;
    }

    public void setLeaveReasonId(int leaveReasonId) {
        this.leaveReasonId = leaveReasonId;
    }

    @Basic
    @Column(name = "reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LeaveReason that = (LeaveReason) o;

        if (leaveReasonId != that.leaveReasonId) return false;
        if (reason != null ? !reason.equals(that.reason) : that.reason != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = leaveReasonId;
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        return result;
    }
}
