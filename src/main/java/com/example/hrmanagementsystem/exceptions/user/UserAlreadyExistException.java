package com.example.hrmanagementsystem.exceptions.user;

import com.example.hrmanagementsystem.exceptions.CustomException;
import com.example.hrmanagementsystem.model.enums.ERRORCODE;

public class UserAlreadyExistException extends CustomException {

    public UserAlreadyExistException(ERRORCODE errorCode) {
        super(errorCode);
    }

    public UserAlreadyExistException(ERRORCODE errorCode, String message) {
        super(errorCode, message);
    }
}
