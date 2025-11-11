package com.example.hrmanagementsystem.service;

import com.example.hrmanagementsystem.exceptions.candidate.CandidateNotFoundException;
import com.example.hrmanagementsystem.mapper.FromEntityToResponse;
import com.example.hrmanagementsystem.mapper.FromRequestToEntity;
import com.example.hrmanagementsystem.model.entity.Candidate;
import com.example.hrmanagementsystem.model.entity.Education;
import com.example.hrmanagementsystem.model.enums.ERRORCODE;
import com.example.hrmanagementsystem.model.request.education.EducationListRequest;
import com.example.hrmanagementsystem.model.response.candidate.CandidateResponseWithEducationsAndTelNo;
import com.example.hrmanagementsystem.repository.CandidateRepository;
import com.example.hrmanagementsystem.repository.EducationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository educationRepository;

    private final CandidateRepository candidateRepository;

    public Education save(Education education){
       return educationRepository.save(education);
    }

    public CandidateResponseWithEducationsAndTelNo addEduInCandidate(Long id, EducationListRequest educations) {
        Candidate candidate = candidateRepository.findById(id).orElseThrow(()-> new CandidateNotFoundException(ERRORCODE.CANDIDATE_NOT_FOUND_EXCEPTION));

        List<Education> educationList = FromRequestToEntity.fromEducationListRequestToEducationEntityMapper(educations.getEducations());
        if (candidate.getEducations()== null){
            candidate.setEducations(new ArrayList<>());
        }

        candidate.getEducations().addAll(educationList);

        candidate.getEducations()
                .forEach(education -> education.setCandidate(candidate));


        Candidate save = candidateRepository.save(candidate);

        return FromEntityToResponse.fromCandidateToCandidateResponseMapper(save);
    }
}
