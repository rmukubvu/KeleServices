package za.co.mabatalale.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by robson on 2017/02/27.
 */
@Entity
@Table(name = "lookup_data", schema = "basil", catalog = "")
public class LookupData {
    private int lookupDataId;
    private Integer lookupKeyId;
    private String value;
    private String valueCode;
    private Integer isActive;
    private Timestamp createdDate;

    @Id
    @Column(name = "lookup_data_id")
    public int getLookupDataId() {
        return lookupDataId;
    }

    public void setLookupDataId(int lookupDataId) {
        this.lookupDataId = lookupDataId;
    }

    @Basic
    @Column(name = "lookup_key_id")
    public Integer getLookupKeyId() {
        return lookupKeyId;
    }

    public void setLookupKeyId(Integer lookupKeyId) {
        this.lookupKeyId = lookupKeyId;
    }

    @Basic
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "value_code")
    public String getValueCode() {
        return valueCode;
    }

    public void setValueCode(String valueCode) {
        this.valueCode = valueCode;
    }

    @Basic
    @Column(name = "is_active")
    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
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

        LookupData that = (LookupData) o;

        if (lookupDataId != that.lookupDataId) return false;
        if (lookupKeyId != null ? !lookupKeyId.equals(that.lookupKeyId) : that.lookupKeyId != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (valueCode != null ? !valueCode.equals(that.valueCode) : that.valueCode != null) return false;
        if (isActive != null ? !isActive.equals(that.isActive) : that.isActive != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lookupDataId;
        result = 31 * result + (lookupKeyId != null ? lookupKeyId.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (valueCode != null ? valueCode.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
