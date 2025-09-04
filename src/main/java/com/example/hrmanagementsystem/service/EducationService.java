package com.example.hrmanagementsystem.service;

import com.example.hrmanagementsystem.exceptions.education.EducationAlreadyExistsException;
import com.example.hrmanagementsystem.model.entity.Education;
import com.example.hrmanagementsystem.model.enums.ERRORCODE;
import com.example.hrmanagementsystem.repository.EducationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository educationRepository;

    public Education save(Education education){
        if(educationRepository.existsById(education.getId())){
            throw new EducationAlreadyExistsException(ERRORCODE.EDUCATION_ALREADY_EXISTS_EXCEPTION);
        }
       return educationRepository.save(education);
    }
}
