package com.example.hrmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {

    private String message;

    private boolean success;

    private T content;

    public static  <T> CommonResponse<T> success(T data){
        return CommonResponse.<T>builder()
                .message("Operations successful")
                .success(true)
                .content(data)
                .build();
    }

    public static  <T> CommonResponse<T> success(String message, T data){
        return CommonResponse.<T>builder()
                .message(message)
                .success(true)
                .content(data)
                .build();
    }

}
