package za.co.mabatalale.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by robson on 2017/02/27.
 */
@Entity
@Table(name = "operator_sign_in", schema = "basil", catalog = "")
public class OperatorSignIn {
    private int operatorSignId;
    private Integer operatorId;
    private Integer assistantId;
    private String shiftCrew;
    private Integer shiftId;
    private Integer rigId;
    private Integer benchSupervisorId;
    private Integer foremanId;
    private double machineOpenHours;
    private double trammingOpenHours;
    private Timestamp createdDate;
    private int dateInt;

    @Id
    @Column(name = "operator_sign_id")
    public int getOperatorSignId() {
        return operatorSignId;
    }

    public void setOperatorSignId(int operatorSignId) {
        this.operatorSignId = operatorSignId;
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
    @Column(name = "assistant_id")
    public Integer getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(Integer assistantId) {
        this.assistantId = assistantId;
    }

    @Basic
    @Column(name = "shift_crew")
    public String getShiftCrew() {
        return shiftCrew;
    }

    public void setShiftCrew(String shiftCrew) {
        this.shiftCrew = shiftCrew;
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
    @Column(name = "rig_id")
    public Integer getRigId() {
        return rigId;
    }

    public void setRigId(Integer rigId) {
        this.rigId = rigId;
    }

    @Basic
    @Column(name = "bench_supervisor_id")
    public Integer getBenchSupervisorId() {
        return benchSupervisorId;
    }

    public void setBenchSupervisorId(Integer benchSupervisorId) {
        this.benchSupervisorId = benchSupervisorId;
    }

    @Basic
    @Column(name = "foreman_id")
    public Integer getForemanId() {
        return foremanId;
    }

    public void setForemanId(Integer foremanId) {
        this.foremanId = foremanId;
    }

    @Basic
    @Column(name = "machine_open_hours")
    public double getMachineOpenHours() {
        return machineOpenHours;
    }

    public void setMachineOpenHours(double machineOpenHours) {
        this.machineOpenHours = machineOpenHours;
    }

    @Basic
    @Column(name = "tramming_open_hours")
    public double getTrammingOpenHours() {
        return trammingOpenHours;
    }

    public void setTrammingOpenHours(double trammingOpenHours) {
        this.trammingOpenHours = trammingOpenHours;
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

        OperatorSignIn that = (OperatorSignIn) o;

        if (operatorSignId != that.operatorSignId) return false;
        if (operatorId != null ? !operatorId.equals(that.operatorId) : that.operatorId != null) return false;
        if (assistantId != null ? !assistantId.equals(that.assistantId) : that.assistantId != null) return false;
        if (shiftCrew != null ? !shiftCrew.equals(that.shiftCrew) : that.shiftCrew != null) return false;
        if (shiftId != null ? !shiftId.equals(that.shiftId) : that.shiftId != null) return false;
        if (rigId != null ? !rigId.equals(that.rigId) : that.rigId != null) return false;
        if (benchSupervisorId != null ? !benchSupervisorId.equals(that.benchSupervisorId) : that.benchSupervisorId != null)
            return false;
        if (foremanId != null ? !foremanId.equals(that.foremanId) : that.foremanId != null) return false;
         if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = operatorSignId;
        result = 31 * result + (operatorId != null ? operatorId.hashCode() : 0);
        result = 31 * result + (assistantId != null ? assistantId.hashCode() : 0);
        result = 31 * result + (shiftCrew != null ? shiftCrew.hashCode() : 0);
        result = 31 * result + (shiftId != null ? shiftId.hashCode() : 0);
        result = 31 * result + (rigId != null ? rigId.hashCode() : 0);
        result = 31 * result + (benchSupervisorId != null ? benchSupervisorId.hashCode() : 0);
        result = 31 * result + (foremanId != null ? foremanId.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "date_int")
    public int getDateInt() {
        return dateInt;
    }

    public void setDateInt(int dateInt) {
        this.dateInt = dateInt;
    }
}
