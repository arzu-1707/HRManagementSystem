package com.example.hrmanagementsystem.model.response;

import com.example.hrmanagementsystem.model.enums.DEGREE;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EducationResponse {

    private Long id;
    @Enumerated(EnumType.STRING)
    private DEGREE degree;

    private String name;

    private String profession;
}
