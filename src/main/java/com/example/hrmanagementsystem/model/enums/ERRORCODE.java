package com.example.hrmanagementsystem.model.enums;

import lombok.Getter;

@Getter
public enum ERRORCODE {
    CANDIDATE_ALREADY_EXISTS_EXCEPTION(1001, "Candidate is already exists"),






    EDUCATION_ALREADY_EXISTS_EXCEPTION(2001, "Candidate is already exists");



    private final String message;
    private final Integer code;
    ERRORCODE( Integer code, String message){
        this.message = message;
        this.code = code;
    }
}
