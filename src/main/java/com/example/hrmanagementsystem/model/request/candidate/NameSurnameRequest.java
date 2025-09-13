package com.example.hrmanagementsystem.model.request.candidate;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NameSurnameRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String surName;
}
