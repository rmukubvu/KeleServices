package za.co.mabatalale.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by robson on 2017/02/27.
 */
@Entity
@Table(name = "devices")
public class Devices {
    private int deviceId;
    private Integer deviceStatusId;
    private String deviceName;
    private Timestamp deviceStartDate;
    private String devicesSerialNumber;
    private String devicesImeiNumber;

    @Id
    @Column(name = "device_id")
    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    @Basic
    @Column(name = "device_status_id")
    public Integer getDeviceStatusId() {
        return deviceStatusId;
    }

    public void setDeviceStatusId(Integer deviceStatusId) {
        this.deviceStatusId = deviceStatusId;
    }

    @Basic
    @Column(name = "device_name")
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Basic
    @Column(name = "device_start_date")
    public Timestamp getDeviceStartDate() {
        return deviceStartDate;
    }

    public void setDeviceStartDate(Timestamp deviceStartDate) {
        this.deviceStartDate = deviceStartDate;
    }

    @Basic
    @Column(name = "devices_serial_number")
    public String getDevicesSerialNumber() {
        return devicesSerialNumber;
    }

    public void setDevicesSerialNumber(String devicesSerialNumber) {
        this.devicesSerialNumber = devicesSerialNumber;
    }

    @Basic
    @Column(name = "devices_imei_number")
    public String getDevicesImeiNumber() {
        return devicesImeiNumber;
    }

    public void setDevicesImeiNumber(String devicesImeiNumber) {
        this.devicesImeiNumber = devicesImeiNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Devices devices = (Devices) o;

        if (deviceId != devices.deviceId) return false;
        if (deviceStatusId != null ? !deviceStatusId.equals(devices.deviceStatusId) : devices.deviceStatusId != null)
            return false;
        if (deviceName != null ? !deviceName.equals(devices.deviceName) : devices.deviceName != null) return false;
        if (deviceStartDate != null ? !deviceStartDate.equals(devices.deviceStartDate) : devices.deviceStartDate != null)
            return false;
        if (devicesSerialNumber != null ? !devicesSerialNumber.equals(devices.devicesSerialNumber) : devices.devicesSerialNumber != null)
            return false;
        if (devicesImeiNumber != null ? !devicesImeiNumber.equals(devices.devicesImeiNumber) : devices.devicesImeiNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = deviceId;
        result = 31 * result + (deviceStatusId != null ? deviceStatusId.hashCode() : 0);
        result = 31 * result + (deviceName != null ? deviceName.hashCode() : 0);
        result = 31 * result + (deviceStartDate != null ? deviceStartDate.hashCode() : 0);
        result = 31 * result + (devicesSerialNumber != null ? devicesSerialNumber.hashCode() : 0);
        result = 31 * result + (devicesImeiNumber != null ? devicesImeiNumber.hashCode() : 0);
        return result;
    }
}
