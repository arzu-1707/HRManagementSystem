package com.example.hrmanagementsystem.service;

import com.example.hrmanagementsystem.model.entity.TelNo;
import com.example.hrmanagementsystem.repository.TelNoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelNoService {

    private final TelNoRepository telNoRepository;

    public TelNo save(TelNo telNo){
        return telNoRepository.save(telNo);
    }
}
