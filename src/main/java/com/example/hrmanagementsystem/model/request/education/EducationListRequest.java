package com.example.hrmanagementsystem.model.request.education;

import lombok.Data;

import java.util.List;

@Data
public class EducationListRequest {
    private List<EducationRequest> educations;
}
