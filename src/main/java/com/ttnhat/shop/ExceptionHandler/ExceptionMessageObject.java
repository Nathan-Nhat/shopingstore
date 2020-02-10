package com.ttnhat.shop.ExceptionHandler;

import java.util.Date;

public class ExceptionMessageObject {
    private Date date;
    private int status;
    private String error;
    private String message;
    private String path;
    private String atLine;
    private String code;
    public ExceptionMessageObject(Date date, int status, String error,String code, String message, String path
    , String atLine) {
        this.date = date;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.atLine = atLine;
        this.code = code;

    }

    public Date getDate() {
        return date;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public String getAtLine() {
        return atLine;
    }

    public String getCode() {
        return code;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setAtLine(String atLine) {
        this.atLine = atLine;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
