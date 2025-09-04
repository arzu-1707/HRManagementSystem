package com.example.hrmanagementsystem.exceptions.candidate;

import com.example.hrmanagementsystem.exceptions.CustomException;
import com.example.hrmanagementsystem.model.enums.ERRORCODE;

public class CandidateNotFoundException extends CustomException {
    public CandidateNotFoundException(ERRORCODE errorCode) {
        super(errorCode);
    }

    public CandidateNotFoundException(ERRORCODE errorCode, String message) {
        super(errorCode, message);
    }
}
