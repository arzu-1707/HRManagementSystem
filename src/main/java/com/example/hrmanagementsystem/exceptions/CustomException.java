package com.example.hrmanagementsystem.exceptions;

import com.example.hrmanagementsystem.model.enums.ERRORCODE;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private final ERRORCODE errorCode;

    public CustomException(ERRORCODE errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public CustomException(ERRORCODE errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }
}
