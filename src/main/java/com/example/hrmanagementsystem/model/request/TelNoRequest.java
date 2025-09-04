package com.example.hrmanagementsystem.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TelNoRequest {
    private String number;
}
