package com.example.hrmanagementsystem.model.request.candidate;

import com.example.hrmanagementsystem.model.enums.GENDER;
import com.example.hrmanagementsystem.model.request.education.EducationRequest;
import com.example.hrmanagementsystem.model.request.telNo.TelNoRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateWithEducationTelNo {

    private String name;

    private String surName;

    @Enumerated(EnumType.STRING)
    private GENDER gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate birthDate;

    private String birthPlace;

    private List<EducationRequest> education;

    private List<TelNoRequest> telNo;
}
