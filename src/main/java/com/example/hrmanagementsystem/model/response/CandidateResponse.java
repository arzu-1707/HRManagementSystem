package com.example.hrmanagementsystem.model.response;

import com.example.hrmanagementsystem.model.enums.GENDER;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    private GENDER gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate birthDate;

    private String birthPlace;

    List<Long> educations = new ArrayList<>();

    List<String> tel = new ArrayList<>();
}
