package com.example.hrmanagementsystem.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateResponse {

    private Long id;
    private String name;

    private String surName;

    private String gender;

    private LocalDate birthDate;

    private String birthPlace;

    List<Long> educations = new ArrayList<>();

    List<String> tel = new ArrayList<>();
}
