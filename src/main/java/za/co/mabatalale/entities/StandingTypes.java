package za.co.mabatalale.entities;

import javax.persistence.*;

/**
 * Created by robson on 2017/02/27.
 */
@Entity
@Table(name = "standing_types", schema = "basil", catalog = "")
public class StandingTypes {
    private int standingTypesId;
    private String typeName;

    @Id
    @Column(name = "standing_types_id")
    public int getStandingTypesId() {
        return standingTypesId;
    }

    public void setStandingTypesId(int standingTypesId) {
        this.standingTypesId = standingTypesId;
    }

    @Basic
    @Column(name = "type_name")
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StandingTypes that = (StandingTypes) o;

        if (standingTypesId != that.standingTypesId) return false;
        if (typeName != null ? !typeName.equals(that.typeName) : that.typeName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = standingTypesId;
        result = 31 * result + (typeName != null ? typeName.hashCode() : 0);
        return result;
    }
}
