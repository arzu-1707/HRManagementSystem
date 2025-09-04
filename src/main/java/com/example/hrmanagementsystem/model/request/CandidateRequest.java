package com.example.hrmanagementsystem.model.request;

import com.example.hrmanagementsystem.model.entity.Education;
import com.example.hrmanagementsystem.model.entity.TelNo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateRequest {

    private String name;

    private String surName;

    private String gender;

    private LocalDate birthDate;

    private String birthPlace;

    private List<EducationRequest> education;

    private List<TelNoRequest> telNo;
}
