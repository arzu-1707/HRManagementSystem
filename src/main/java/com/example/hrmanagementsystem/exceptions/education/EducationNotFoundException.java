package com.example.hrmanagementsystem.exceptions.education;

import com.example.hrmanagementsystem.exceptions.CustomException;
import com.example.hrmanagementsystem.model.enums.ERRORCODE;

public class EducationNotFoundException extends CustomException {
    public EducationNotFoundException(ERRORCODE errorCode) {
        super(errorCode);
    }

    public EducationNotFoundException(ERRORCODE errorCode, String message) {
        super(errorCode, message);
    }
}
