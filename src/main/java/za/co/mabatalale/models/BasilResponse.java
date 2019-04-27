package za.co.mabatalale.models;

/**
 * Created by robson on 2016/12/16.
 */
public class BasilResponse {
    private String responseMessage;
    private boolean isError;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }
}
