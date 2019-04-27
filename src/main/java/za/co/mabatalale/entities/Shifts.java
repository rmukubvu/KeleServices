package za.co.mabatalale.entities;

import javax.persistence.*;

/**
 * Created by robson on 2017/02/27.
 */
@Entity
@Table(name = "shifts")
public class Shifts {
    private int shiftId;
    private String shiftName;
    private String shiftsStartTime;
    private String shiftsEndTime;

    @Id
    @Column(name = "shift_id")
    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    @Basic
    @Column(name = "shift_name")
    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    @Basic
    @Column(name = "shifts_start_time")
    public String getShiftsStartTime() {
        return shiftsStartTime;
    }

    public void setShiftsStartTime(String shiftsStartTime) {
        this.shiftsStartTime = shiftsStartTime;
    }

    @Basic
    @Column(name = "shifts_end_time")
    public String getShiftsEndTime() {
        return shiftsEndTime;
    }

    public void setShiftsEndTime(String shiftsEndTime) {
        this.shiftsEndTime = shiftsEndTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shifts shifts = (Shifts) o;

        if (shiftId != shifts.shiftId) return false;
        if (shiftName != null ? !shiftName.equals(shifts.shiftName) : shifts.shiftName != null) return false;
        if (shiftsStartTime != null ? !shiftsStartTime.equals(shifts.shiftsStartTime) : shifts.shiftsStartTime != null)
            return false;
        if (shiftsEndTime != null ? !shiftsEndTime.equals(shifts.shiftsEndTime) : shifts.shiftsEndTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = shiftId;
        result = 31 * result + (shiftName != null ? shiftName.hashCode() : 0);
        result = 31 * result + (shiftsStartTime != null ? shiftsStartTime.hashCode() : 0);
        result = 31 * result + (shiftsEndTime != null ? shiftsEndTime.hashCode() : 0);
        return result;
    }
}
