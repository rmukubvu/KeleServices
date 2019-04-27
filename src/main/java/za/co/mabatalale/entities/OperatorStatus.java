package za.co.mabatalale.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by robson on 2017/02/27.
 */
@Entity
@Table(name = "operator_status", schema = "basil", catalog = "")
public class OperatorStatus {
    private int operatorStatusId;
    private Integer lookUpId;
    private Integer operatorId;
    private String plantNumber;
    private Timestamp statusDate;
    private String actualStatus;
    private Integer dateInt;

    @Id
    @Column(name = "operator_status_id")
    public int getOperatorStatusId() {
        return operatorStatusId;
    }

    public void setOperatorStatusId(int operatorStatusId) {
        this.operatorStatusId = operatorStatusId;
    }

    @Basic
    @Column(name = "look_up_id")
    public Integer getLookUpId() {
        return lookUpId;
    }

    public void setLookUpId(Integer lookUpId) {
        this.lookUpId = lookUpId;
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
    @Column(name = "plant_number")
    public String getPlantNumber() {
        return plantNumber;
    }

    public void setPlantNumber(String plantNumber) {
        this.plantNumber = plantNumber;
    }

    @Basic
    @Column(name = "status_date")
    public Timestamp getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Timestamp statusDate) {
        this.statusDate = statusDate;
    }

    @Basic
    @Column(name = "actual_status")
    public String getActualStatus() {
        return actualStatus;
    }

    public void setActualStatus(String actualStatus) {
        this.actualStatus = actualStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperatorStatus that = (OperatorStatus) o;

        if (operatorStatusId != that.operatorStatusId) return false;
        if (lookUpId != null ? !lookUpId.equals(that.lookUpId) : that.lookUpId != null) return false;
        if (operatorId != null ? !operatorId.equals(that.operatorId) : that.operatorId != null) return false;
        if (plantNumber != null ? !plantNumber.equals(that.plantNumber) : that.plantNumber != null) return false;
        if (statusDate != null ? !statusDate.equals(that.statusDate) : that.statusDate != null) return false;
        if (actualStatus != null ? !actualStatus.equals(that.actualStatus) : that.actualStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = operatorStatusId;
        result = 31 * result + (lookUpId != null ? lookUpId.hashCode() : 0);
        result = 31 * result + (operatorId != null ? operatorId.hashCode() : 0);
        result = 31 * result + (plantNumber != null ? plantNumber.hashCode() : 0);
        result = 31 * result + (statusDate != null ? statusDate.hashCode() : 0);
        result = 31 * result + (actualStatus != null ? actualStatus.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "date_int")
    public Integer getDateInt() {
        return dateInt;
    }

    public void setDateInt(Integer dateInt) {
        this.dateInt = dateInt;
    }
}
