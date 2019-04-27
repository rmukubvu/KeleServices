package za.co.mabatalale.entities;

import javax.persistence.*;

/**
 * Created by robson on 2017/02/27.
 */
@Entity
@Table(name = "role_types", schema = "basil", catalog = "")
public class RoleTypes {
    private int roleTypesId;
    private String roleTypeName;

    @Id
    @Column(name = "role_types_id")
    public int getRoleTypesId() {
        return roleTypesId;
    }

    public void setRoleTypesId(int roleTypesId) {
        this.roleTypesId = roleTypesId;
    }

    @Basic
    @Column(name = "role_type_name")
    public String getRoleTypeName() {
        return roleTypeName;
    }

    public void setRoleTypeName(String roleTypeName) {
        this.roleTypeName = roleTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleTypes roleTypes = (RoleTypes) o;

        if (roleTypesId != roleTypes.roleTypesId) return false;
        if (roleTypeName != null ? !roleTypeName.equals(roleTypes.roleTypeName) : roleTypes.roleTypeName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleTypesId;
        result = 31 * result + (roleTypeName != null ? roleTypeName.hashCode() : 0);
        return result;
    }
}
