package za.co.mabatalale.entities;

import javax.persistence.*;

/**
 * Created by robson on 2017/02/27.
 */
@Entity
@Table(name = "device_status", schema = "basil", catalog = "")
public class DeviceStatus {
    private int deviceStatusId;
    private String deviceStatusText;

    @Id
    @Column(name = "device_status_id")
    public int getDeviceStatusId() {
        return deviceStatusId;
    }

    public void setDeviceStatusId(int deviceStatusId) {
        this.deviceStatusId = deviceStatusId;
    }

    @Basic
    @Column(name = "device_status_text")
    public String getDeviceStatusText() {
        return deviceStatusText;
    }

    public void setDeviceStatusText(String deviceStatusText) {
        this.deviceStatusText = deviceStatusText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceStatus that = (DeviceStatus) o;

        if (deviceStatusId != that.deviceStatusId) return false;
        if (deviceStatusText != null ? !deviceStatusText.equals(that.deviceStatusText) : that.deviceStatusText != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = deviceStatusId;
        result = 31 * result + (deviceStatusText != null ? deviceStatusText.hashCode() : 0);
        return result;
    }
}
