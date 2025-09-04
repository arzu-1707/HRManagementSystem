package com.example.hrmanagementsystem.exceptions.candidate;

import com.example.hrmanagementsystem.exceptions.CustomException;
import com.example.hrmanagementsystem.model.enums.ERRORCODE;

public class CandidateAlreadyExistsException extends CustomException {
    public CandidateAlreadyExistsException(ERRORCODE errorCode){
        super(errorCode);
    }

    public CandidateAlreadyExistsException(ERRORCODE errorCode, String message){
        super(errorCode, message);
    }
}
