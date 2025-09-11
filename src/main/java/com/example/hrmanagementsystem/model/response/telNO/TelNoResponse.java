package com.example.hrmanagementsystem.model.response.telNO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TelNoResponse {
    private Long id;

    private String number;
}
