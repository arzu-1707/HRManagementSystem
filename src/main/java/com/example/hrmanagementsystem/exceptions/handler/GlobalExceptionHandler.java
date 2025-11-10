package com.example.hrmanagementsystem.exceptions.handler;

import com.example.hrmanagementsystem.exceptions.ErrorResponse;
import com.example.hrmanagementsystem.exceptions.candidate.CandidateAlreadyExistsException;
import com.example.hrmanagementsystem.exceptions.candidate.CandidateNotFoundException;
import com.example.hrmanagementsystem.exceptions.candidate.CandidatesNotFoundException;
import com.example.hrmanagementsystem.exceptions.education.EducationAlreadyExistsException;
import com.example.hrmanagementsystem.exceptions.education.EducationNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {


   private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

                    //  Candidate Exceptions
   @ExceptionHandler(CandidateNotFoundException.class)
    public ResponseEntity<ErrorResponse> candidateNotFoundExceptionHandler(
         CandidateNotFoundException ex, HttpServletRequest httpRequest
    ){
        logger.error("CandidateNotFoundException : {} " , ex.getMessage() + ex);
        ErrorResponse errorResponse = ErrorResponse.of(ex.getErrorCode(), ex.getMessage(), httpRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(CandidatesNotFoundException.class)
    public ResponseEntity<ErrorResponse> candidatesNotFoundExceptionHandler(
            CandidatesNotFoundException ex, HttpServletRequest httpServletRequest
    ){
       logger.error("CandidatesNotFoundException: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = ErrorResponse.of(ex.getErrorCode(), ex.getMessage(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(CandidateAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> candidateAlreadyExistsExceptionHandler(
            CandidateAlreadyExistsException ex, HttpServletRequest httpServletRequest
    ){
       logger.error("CandidateAlreadyExistsException: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = ErrorResponse.of(ex.getErrorCode(), ex.getMessage(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(errorResponse);
    }


    //                    Educations Exception
    @ExceptionHandler(EducationNotFoundException.class)
    public ResponseEntity<ErrorResponse> educationNotFoundExceptionHandler(
            EducationNotFoundException ex, HttpServletRequest httpServletRequest
    ){
       logger.error("EducationNotFoundException: {} ", ex.getMessage(), ex);
        ErrorResponse errorResponse = ErrorResponse.of(ex.getErrorCode(), ex.getMessage(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
   }

   @ExceptionHandler(EducationAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> educationAlreadyExceptionHandler(
            EducationAlreadyExistsException ex, HttpServletRequest httpServletRequest
   ){
       logger.error("ExceptionAlreadyException: {} ", ex.getMessage(), ex);
       ErrorResponse errorResponse = ErrorResponse.of(ex.getErrorCode(), ex.getMessage(), httpServletRequest.getRequestURI());
       return ResponseEntity.status(HttpStatus.CONFLICT)
               .body(errorResponse);
   }


}
