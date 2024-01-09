package com.anjaniy.creditcardmanagementsystem.models.dto.response;

public class ExceptionResponse {

    private String exceptionMessage;

    private String dateTime;

    public ExceptionResponse() {
    }

    public ExceptionResponse(
            String exceptionMessage,
            String dateTime
    )
    {
        this.exceptionMessage = exceptionMessage;
        this.dateTime = dateTime;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "exceptionMessage='" + exceptionMessage + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }

}
