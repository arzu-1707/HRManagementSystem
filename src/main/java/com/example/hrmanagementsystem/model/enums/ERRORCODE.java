package com.example.hrmanagementsystem.model.enums;

import lombok.Getter;

@Getter
public enum ERRORCODE {
    CANDIDATE_NOT_FOUND_EXCEPTION(1001, "Candidate is not found"),
    CANDIDATES_NOT_FOUND_EXCEPTION(1002, "Candidates is not found"),
    CANDIDATE_ALREADY_EXISTS_EXCEPTION(1003, "Candidate is already exists"),





    EDUCATION_NOT_FOUND_EXCEPTION(2001, "Education not found"),
    EDUCATIONS_NOT_FOUND_EXCEPTION(2002, "Educations not found"),
    EDUCATION_ALREADY_EXISTS_EXCEPTION(2003, "Candidate is already exists"),



    USER_NOT_FOUND_EXCEPTION(3000, "User not found"),
    USER_ALREADY_FOUND_EXCEPTION(3001, "User already found");

    private final String message;
    private final Integer code;
    ERRORCODE( Integer code, String message){
        this.message = message;
        this.code = code;
    }
}
