package za.co.mabatalale.entities;

import javax.persistence.*;

/**
 * Created by robson on 2017/02/27.
 */
@Entity
@Table(name = "breakdown_types", schema = "basil", catalog = "")
public class BreakdownTypes {
    private int breakdownTypesId;
    private String typeText;

    @Id
    @Column(name = "breakdown_types_id")
    public int getBreakdownTypesId() {
        return breakdownTypesId;
    }

    public void setBreakdownTypesId(int breakdownTypesId) {
        this.breakdownTypesId = breakdownTypesId;
    }

    @Basic
    @Column(name = "type_text")
    public String getTypeText() {
        return typeText;
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BreakdownTypes that = (BreakdownTypes) o;

        if (breakdownTypesId != that.breakdownTypesId) return false;
        if (typeText != null ? !typeText.equals(that.typeText) : that.typeText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = breakdownTypesId;
        result = 31 * result + (typeText != null ? typeText.hashCode() : 0);
        return result;
    }
}
