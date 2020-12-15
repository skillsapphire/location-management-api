package com.mycompany.locationmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorModel> handleBusinessException(BusinessException be){
        ResponseEntity re = null;
        if(be.getErrorModel().getCode() == 404) {
            re = new ResponseEntity<ErrorModel>(be.getErrorModel(), HttpStatus.NOT_FOUND);
        }
        return re;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorModel> handleException(Exception ex){
        ErrorModel error = new ErrorModel(HttpStatus.BAD_REQUEST.value(), "The request could not be understood by the server due to malformed syntax.");
        return new ResponseEntity<ErrorModel>(error, HttpStatus.BAD_REQUEST);
    }
}
