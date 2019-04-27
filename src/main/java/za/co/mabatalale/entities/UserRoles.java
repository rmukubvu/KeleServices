package za.co.mabatalale.entities;

import javax.persistence.*;

/**
 * Created by robson on 2017/02/27.
 */
@Entity
@Table(name = "user_roles", schema = "basil", catalog = "")
public class UserRoles {
    private int rolesId;
    private String roleName;

    @Id
    @Column(name = "roles_id")
    public int getRolesId() {
        return rolesId;
    }

    public void setRolesId(int rolesId) {
        this.rolesId = rolesId;
    }

    @Basic
    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoles userRoles = (UserRoles) o;

        if (rolesId != userRoles.rolesId) return false;
        if (roleName != null ? !roleName.equals(userRoles.roleName) : userRoles.roleName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rolesId;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }
}
