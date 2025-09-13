package com.example.hrmanagementsystem.model.request.candidate;

import com.example.hrmanagementsystem.model.enums.GENDER;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CandidateRequest {
    private String name;

    private String surName;

    @Enumerated(EnumType.STRING)
    private GENDER gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate birthDate;

    private String birthPlace;
}
