package com.example.hrmanagementsystem.model.request;

import com.example.hrmanagementsystem.model.enums.DEGREE;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class EducationRequest {
    @Enumerated(EnumType.STRING)
    private DEGREE degree;

    private String name;

    private String profession;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate startEdu;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate endEdu;
}
