package com.mycompany.locationmanagement.exception;

public class ErrorModel {

    private int code;
    private String msg;

    public ErrorModel(){}

    public ErrorModel(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }
}
