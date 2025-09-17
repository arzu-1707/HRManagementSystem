package com.example.hrmanagementsystem.model.request.telNo;

import lombok.Data;

import java.util.List;

@Data
public class TelNoListRequest {
    private List<TelNoRequest> telNoRequests;
}
