package za.co.mabatalale.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by robson on 2017/03/02.
 */
@Entity
@Table(name = "end_of_shift")
public class EndOfShift {

    private int endOfShiftId;
    private int operatorId;
    private int rigId;
    private Timestamp endTime;
    private String reason;
    private double machineClosing;
    private double trammingClosing;
    private int dateInt;
    private String sessionKey;

    @Id
    @Column(name = "end_of_shift_id")
    public int getEndOfShiftId() {
        return endOfShiftId;
    }

    public void setEndOfShiftId(int endOfShiftId) {
        this.endOfShiftId = endOfShiftId;
    }

    @Basic
    @Column(name = "operator_id")
    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    @Basic
    @Column(name = "rig_id")
    public int getRigId() {
        return rigId;
    }

    public void setRigId(int rigId) {
        this.rigId = rigId;
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
    @Column(name = "reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Basic
    @Column(name = "date_int")
    public int getDateInt() {
        return dateInt;
    }

    public void setDateInt(int dateInt) {
        this.dateInt = dateInt;
    }

    @Basic
    @Column(name = "machine_closing")
    public double getMachineClosing() {
        return machineClosing;
    }

    public void setMachineClosing(double machineClosing) {
        this.machineClosing = machineClosing;
    }

    @Basic
    @Column(name = "tramming_closing")
    public double getTrammingClosing() {
        return trammingClosing;
    }

    public void setTrammingClosing(double trammingClosing) {
        this.trammingClosing = trammingClosing;
    }

    @Basic
    @Column(name = "session_key")
    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
