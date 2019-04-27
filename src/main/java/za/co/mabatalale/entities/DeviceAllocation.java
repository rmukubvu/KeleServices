package za.co.mabatalale.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by robson on 2017/02/27.
 */
@Entity
@Table(name = "device_allocation", schema = "basil", catalog = "")
public class DeviceAllocation {
    private int deviceAllocationId;
    private Integer userId;
    private Timestamp allocatedOn;
    private Timestamp endDate;

    @Id
    @Column(name = "device_allocation_id")
    public int getDeviceAllocationId() {
        return deviceAllocationId;
    }

    public void setDeviceAllocationId(int deviceAllocationId) {
        this.deviceAllocationId = deviceAllocationId;
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
    @Column(name = "allocated_on")
    public Timestamp getAllocatedOn() {
        return allocatedOn;
    }

    public void setAllocatedOn(Timestamp allocatedOn) {
        this.allocatedOn = allocatedOn;
    }

    @Basic
    @Column(name = "end_date")
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceAllocation that = (DeviceAllocation) o;

        if (deviceAllocationId != that.deviceAllocationId) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (allocatedOn != null ? !allocatedOn.equals(that.allocatedOn) : that.allocatedOn != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = deviceAllocationId;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (allocatedOn != null ? allocatedOn.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }
}
