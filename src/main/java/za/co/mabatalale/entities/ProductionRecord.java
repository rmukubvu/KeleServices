package za.co.mabatalale.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by robson on 2017/02/27.
 */
@Entity
@Table(name = "production_record", schema = "basil", catalog = "")
public class ProductionRecord {
    private int productionRecordId;
    private Integer operatorId;
    private Integer rigId;
    private String drillType;
    private Integer statusId;
    private String bitSize;
    private String rodSize;
    private String benchNumber;
    private String benchEndNumber;
    private String holeNumber;
    private String holeDepthRequired;
    private Integer holes;
    private BigDecimal meters;
    private Timestamp startTime;
    private Timestamp endTime;
    private Timestamp createdDate;
    private Integer dateInt;

    @Basic
    @Column(name = "holes")
    public Integer getHoles() {
        return holes;
    }

    public void setHoles(Integer holes) {
        this.holes = holes;
    }

    @Basic
    @Column(name = "meters")
    public BigDecimal getMeters() {
        return meters;
    }

    public void setMeters(BigDecimal meters) {
        this.meters = meters;
    }

    @Basic
    @Column(name = "start_time")
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }



    @Id
    @Column(name = "production_record_id")
    public int getProductionRecordId() {
        return productionRecordId;
    }

    public void setProductionRecordId(int productionRecordId) {
        this.productionRecordId = productionRecordId;
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
    @Column(name = "rig_id")
    public Integer getRigId() {
        return rigId;
    }

    public void setRigId(Integer rigId) {
        this.rigId = rigId;
    }

    @Basic
    @Column(name = "drill_type")
    public String getDrillType() {
        return drillType;
    }

    public void setDrillType(String drillType) {
        this.drillType = drillType;
    }

    @Basic
    @Column(name = "status_id")
    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    @Basic
    @Column(name = "bit_size")
    public String getBitSize() {
        return bitSize;
    }

    public void setBitSize(String bitSize) {
        this.bitSize = bitSize;
    }

    @Basic
    @Column(name = "rod_size")
    public String getRodSize() {
        return rodSize;
    }

    public void setRodSize(String rodSize) {
        this.rodSize = rodSize;
    }

    @Basic
    @Column(name = "bench_number")
    public String getBenchNumber() {
        return benchNumber;
    }

    public void setBenchNumber(String benchNumber) {
        this.benchNumber = benchNumber;
    }

    @Basic
    @Column(name = "hole_number")
    public String getHoleNumber() {
        return holeNumber;
    }

    public void setHoleNumber(String holeNumber) {
        this.holeNumber = holeNumber;
    }

    @Basic
    @Column(name = "hole_depth_required")
    public String getHoleDepthRequired() {
        return holeDepthRequired;
    }

    public void setHoleDepthRequired(String holeDepthRequired) {
        this.holeDepthRequired = holeDepthRequired;
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

        ProductionRecord that = (ProductionRecord) o;

        if (productionRecordId != that.productionRecordId) return false;
        if (operatorId != null ? !operatorId.equals(that.operatorId) : that.operatorId != null) return false;
        if (rigId != null ? !rigId.equals(that.rigId) : that.rigId != null) return false;
        if (drillType != null ? !drillType.equals(that.drillType) : that.drillType != null) return false;
        if (statusId != null ? !statusId.equals(that.statusId) : that.statusId != null) return false;
        if (bitSize != null ? !bitSize.equals(that.bitSize) : that.bitSize != null) return false;
        if (rodSize != null ? !rodSize.equals(that.rodSize) : that.rodSize != null) return false;
        if (benchNumber != null ? !benchNumber.equals(that.benchNumber) : that.benchNumber != null) return false;
        if (holeNumber != null ? !holeNumber.equals(that.holeNumber) : that.holeNumber != null) return false;
        if (holeDepthRequired != null ? !holeDepthRequired.equals(that.holeDepthRequired) : that.holeDepthRequired != null)
            return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productionRecordId;
        result = 31 * result + (operatorId != null ? operatorId.hashCode() : 0);
        result = 31 * result + (rigId != null ? rigId.hashCode() : 0);
        result = 31 * result + (drillType != null ? drillType.hashCode() : 0);
        result = 31 * result + (statusId != null ? statusId.hashCode() : 0);
        result = 31 * result + (bitSize != null ? bitSize.hashCode() : 0);
        result = 31 * result + (rodSize != null ? rodSize.hashCode() : 0);
        result = 31 * result + (benchNumber != null ? benchNumber.hashCode() : 0);
        result = 31 * result + (holeNumber != null ? holeNumber.hashCode() : 0);
        result = 31 * result + (holeDepthRequired != null ? holeDepthRequired.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
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

    @Basic
    @Column(name = "bench_end_number")
    public String getBenchEndNumber() {
        return benchEndNumber;
    }

    public void setBenchEndNumber(String benchEndNumber) {
        this.benchEndNumber = benchEndNumber;
    }
}
