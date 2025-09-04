package com.example.hrmanagementsystem.exceptions.education;

import com.example.hrmanagementsystem.exceptions.CustomException;
import com.example.hrmanagementsystem.model.enums.ERRORCODE;

public class EducationAlreadyExistsException extends CustomException {
    public EducationAlreadyExistsException(ERRORCODE errorCode) {
        super(errorCode);
    }

    public EducationAlreadyExistsException(ERRORCODE errorCode, String message) {
        super(errorCode, message);
    }
}
