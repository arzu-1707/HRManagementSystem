package com.example.hrmanagementsystem.exceptions;

import ch.qos.logback.core.spi.ErrorCodes;
import com.example.hrmanagementsystem.model.enums.ERRORCODE;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private LocalDateTime time;
    private boolean success;
    private int errorCode;
    private String message;
    private String path;

    public static ErrorResponse of(ERRORCODE errorCode, String path){
        return ErrorResponse.builder()
                .time(LocalDateTime.now())
                .success(false)
                .errorCode(errorCode.getCode())
                .message(errorCode.getMessage())
                .path(path)
                .build();
    }

    public static  ErrorResponse of(ERRORCODE errorcode, String message, String path){
        return ErrorResponse.builder()
                .time(LocalDateTime.now())
                .success(false)
                .errorCode(errorcode.getCode())
                .message(message)
                .path(path)
                .build();
    }
}
