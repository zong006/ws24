package vttp.paf.workshop24.exception;

import java.util.Date;

public class ErrorMessage {
    
    private int status;
    private String errorMessage;
    private Date timeStamp;
    private String endPoint;
    
    public ErrorMessage(int status, String errorMessage, Date timeStamp, String endPoint) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.timeStamp = timeStamp;
        this.endPoint = endPoint;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public Date getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
    public String getEndPoint() {
        return endPoint;
    }
    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }
    @Override
    public String toString() {
        return "ErrorMessage [status=" + status + ", errorMessage=" + errorMessage + ", timeStamp=" + timeStamp
                + ", endPoint=" + endPoint + "]";
    }
}
