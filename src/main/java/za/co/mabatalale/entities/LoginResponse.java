package za.co.mabatalale.entities;

/**
 * Created by robson on 2016/12/28.
 */
public class LoginResponse {
    private boolean isAllowed;
    private String errorMessage;

    public boolean isAllowed() {
        return isAllowed;
    }

    public void setAllowed(boolean allowed) {
        isAllowed = allowed;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
