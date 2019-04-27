package za.co.mabatalale.models;

/**
 * Created by robson on 2016/12/16.
 */
public class ResponseError {
    private String responseMessage;
    private boolean isError;

    public ResponseError(String message) {
        this.responseMessage = message;
        this.setError(true);
    }
    public ResponseError(String message, String... args) {
        this.responseMessage = String.format(message, args);
        this.setError(true);
    }
    public ResponseError(Exception e) {
        this.responseMessage = e.getMessage();
        this.setError(true);
    }
    public String getResponseMessage() {
        return this.responseMessage;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }
}
