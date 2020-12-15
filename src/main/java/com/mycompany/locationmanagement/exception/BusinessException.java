package com.mycompany.locationmanagement.exception;

public class BusinessException extends Exception{

    private ErrorModel errorModel;

    public BusinessException(){}

    public BusinessException(ErrorModel errorModel){
        this.errorModel = errorModel;
    }

    public ErrorModel getErrorModel() {
        return errorModel;
    }
}
