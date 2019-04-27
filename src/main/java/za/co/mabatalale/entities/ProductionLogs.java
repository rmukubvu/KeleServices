package za.co.mabatalale.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by robson on 2017/02/27.
 */
@Entity
@Table(name = "production_logs", schema = "basil", catalog = "")
public class ProductionLogs {
    private int productionLogsId;
    private Integer operatorSheetId;
    private Integer productionTypeId;
    private String benchNumber;
    private Integer holes;
    private BigDecimal meters;
    private Timestamp startTime;
    private Timestamp endTime;
    private Timestamp createdDate;

    @Id
    @Column(name = "production_logs_id")
    public int getProductionLogsId() {
        return productionLogsId;
    }

    public void setProductionLogsId(int productionLogsId) {
        this.productionLogsId = productionLogsId;
    }

    @Basic
    @Column(name = "operator_sheet_id")
    public Integer getOperatorSheetId() {
        return operatorSheetId;
    }

    public void setOperatorSheetId(Integer operatorSheetId) {
        this.operatorSheetId = operatorSheetId;
    }

    @Basic
    @Column(name = "production_type_id")
    public Integer getProductionTypeId() {
        return productionTypeId;
    }

    public void setProductionTypeId(Integer productionTypeId) {
        this.productionTypeId = productionTypeId;
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

        ProductionLogs that = (ProductionLogs) o;

        if (productionLogsId != that.productionLogsId) return false;
        if (operatorSheetId != null ? !operatorSheetId.equals(that.operatorSheetId) : that.operatorSheetId != null)
            return false;
        if (productionTypeId != null ? !productionTypeId.equals(that.productionTypeId) : that.productionTypeId != null)
            return false;
        if (benchNumber != null ? !benchNumber.equals(that.benchNumber) : that.benchNumber != null) return false;
        if (holes != null ? !holes.equals(that.holes) : that.holes != null) return false;
        if (meters != null ? !meters.equals(that.meters) : that.meters != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productionLogsId;
        result = 31 * result + (operatorSheetId != null ? operatorSheetId.hashCode() : 0);
        result = 31 * result + (productionTypeId != null ? productionTypeId.hashCode() : 0);
        result = 31 * result + (benchNumber != null ? benchNumber.hashCode() : 0);
        result = 31 * result + (holes != null ? holes.hashCode() : 0);
        result = 31 * result + (meters != null ? meters.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
