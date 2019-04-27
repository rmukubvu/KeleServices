package za.co.mabatalale.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by robson on 2017/02/27.
 */
@Entity
@Table(name = "operator_sheet", schema = "basil", catalog = "")
public class OperatorSheet {
    private int operatorSheetId;
    private Integer operatorId;
    private Integer shiftId;
    private Timestamp shiftStartDate;
    private Timestamp shiftEndTime;
    private Timestamp createdDate;

    @Id
    @Column(name = "operator_sheet_id")
    public int getOperatorSheetId() {
        return operatorSheetId;
    }

    public void setOperatorSheetId(int operatorSheetId) {
        this.operatorSheetId = operatorSheetId;
    }

    @Basic
    @Column(name = "operator_id")
    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    @Basic
    @Column(name = "shift_id")
    public Integer getShiftId() {
        return shiftId;
    }

    public void setShiftId(Integer shiftId) {
        this.shiftId = shiftId;
    }

    @Basic
    @Column(name = "shift_start_date")
    public Timestamp getShiftStartDate() {
        return shiftStartDate;
    }

    public void setShiftStartDate(Timestamp shiftStartDate) {
        this.shiftStartDate = shiftStartDate;
    }

    @Basic
    @Column(name = "shift_end_time")
    public Timestamp getShiftEndTime() {
        return shiftEndTime;
    }

    public void setShiftEndTime(Timestamp shiftEndTime) {
        this.shiftEndTime = shiftEndTime;
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

        OperatorSheet that = (OperatorSheet) o;

        if (operatorSheetId != that.operatorSheetId) return false;
        if (operatorId != null ? !operatorId.equals(that.operatorId) : that.operatorId != null) return false;
        if (shiftId != null ? !shiftId.equals(that.shiftId) : that.shiftId != null) return false;
        if (shiftStartDate != null ? !shiftStartDate.equals(that.shiftStartDate) : that.shiftStartDate != null)
            return false;
        if (shiftEndTime != null ? !shiftEndTime.equals(that.shiftEndTime) : that.shiftEndTime != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = operatorSheetId;
        result = 31 * result + (operatorId != null ? operatorId.hashCode() : 0);
        result = 31 * result + (shiftId != null ? shiftId.hashCode() : 0);
        result = 31 * result + (shiftStartDate != null ? shiftStartDate.hashCode() : 0);
        result = 31 * result + (shiftEndTime != null ? shiftEndTime.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
