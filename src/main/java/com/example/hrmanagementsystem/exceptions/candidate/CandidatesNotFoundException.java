package com.example.hrmanagementsystem.exceptions.candidate;

import com.example.hrmanagementsystem.exceptions.CustomException;
import com.example.hrmanagementsystem.model.enums.ERRORCODE;

public class CandidatesNotFoundException extends CustomException {
    public CandidatesNotFoundException(ERRORCODE errorCode) {
        super(errorCode);
    }

    public CandidatesNotFoundException(ERRORCODE errorCode, String message) {
        super(errorCode, message);
    }
}
