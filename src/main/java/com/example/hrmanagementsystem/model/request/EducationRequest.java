package com.example.hrmanagementsystem.model.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class EducationRequest {
    private String degree;

    private String name;

    private String profession;

    private LocalDate startEdu;

    private LocalDate endEdu;
}
