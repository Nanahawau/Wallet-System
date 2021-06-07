package com.fgt.walletsystem.models;

import java.util.List;


public class ErrorResponse {

    private int statusCode;
    private String message;
    private List<String> details;


    public ErrorResponse() {
    }

    public ErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ErrorResponse(String message, List<String> details, int statusCode) {
        this.statusCode = statusCode;
        this.message = message;
        this.details = details;
    }




    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", details=" + details +
                '}';
    }
}
