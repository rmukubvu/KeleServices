package za.co.mabatalale.entities;

import javax.persistence.*;

/**
 * Created by robson on 2017/02/27.
 */
@Entity
@Table(name = "basil_sys_user", schema = "basil", catalog = "")
public class BasilSysUser {
    private int basilSysUserId;
    private Integer basilUserId;
    private String userName;
    private String password;
    private Integer passwordRequired;

    @Id
    @Column(name = "basil_sys_user_id")
    public int getBasilSysUserId() {
        return basilSysUserId;
    }

    public void setBasilSysUserId(int basilSysUserId) {
        this.basilSysUserId = basilSysUserId;
    }

    @Basic
    @Column(name = "basil_user_id")
    public Integer getBasilUserId() {
        return basilUserId;
    }

    public void setBasilUserId(Integer basilUserId) {
        this.basilUserId = basilUserId;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "password_required")
    public Integer getPasswordRequired() {
        return passwordRequired;
    }

    public void setPasswordRequired(Integer passwordRequired) {
        this.passwordRequired = passwordRequired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasilSysUser that = (BasilSysUser) o;

        if (basilSysUserId != that.basilSysUserId) return false;
        if (basilUserId != null ? !basilUserId.equals(that.basilUserId) : that.basilUserId != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (passwordRequired != null ? !passwordRequired.equals(that.passwordRequired) : that.passwordRequired != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = basilSysUserId;
        result = 31 * result + (basilUserId != null ? basilUserId.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (passwordRequired != null ? passwordRequired.hashCode() : 0);
        return result;
    }
}
