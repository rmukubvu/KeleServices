package za.co.mabatalale.models;


/**
 * Created by robson on 2017/03/23.
 */
public class LeaveViewModel {
    private int basilUsersLeaveId;
    private String reason;
    private String user;
    private String startDate;
    private String endDate;
    private String createdDate;
    private int dateInt;

    public int getBasilUsersLeaveId() {
        return basilUsersLeaveId;
    }

    public void setBasilUsersLeaveId(int basilUsersLeaveId) {
        this.basilUsersLeaveId = basilUsersLeaveId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getDateInt() {
        return dateInt;
    }

    public void setDateInt(int dateInt) {
        this.dateInt = dateInt;
    }
}
