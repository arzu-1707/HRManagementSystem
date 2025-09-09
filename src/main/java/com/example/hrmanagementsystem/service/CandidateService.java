package com.example.hrmanagementsystem.service;

import com.example.hrmanagementsystem.Mapper.FromEntityToResponse;
import com.example.hrmanagementsystem.Mapper.FromRequestToEntity;
import com.example.hrmanagementsystem.exceptions.candidate.CandidateAlreadyExistsException;
import com.example.hrmanagementsystem.exceptions.candidate.CandidateNotFoundException;
import com.example.hrmanagementsystem.exceptions.candidate.CandidatesNotFoundException;
import com.example.hrmanagementsystem.model.entity.Candidate;
import com.example.hrmanagementsystem.model.entity.Education;
import com.example.hrmanagementsystem.model.entity.TelNo;
import com.example.hrmanagementsystem.model.enums.ERRORCODE;
import com.example.hrmanagementsystem.model.request.CandidateRequest;
import com.example.hrmanagementsystem.model.request.EducationRequest;
import com.example.hrmanagementsystem.model.request.TelNoRequest;
import com.example.hrmanagementsystem.model.response.CandidateResponse;
import com.example.hrmanagementsystem.repository.CandidateRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateService {
    private final CandidateRepository candidateRepository;
    private final EducationService educationService;
    private final TelNoService telNoService;

    public Page<CandidateResponse> findALl(Pageable page) {
        Page<Candidate> all = candidateRepository.findAll(page);
        if (all.isEmpty()){
            throw new CandidatesNotFoundException(ERRORCODE.CANDIDATES_NOT_FOUND_EXCEPTION);
        }
        return FromEntityToResponse.fromCandidateResponseToPageCandidateMapper(all);
    }

    @Transactional
    public CandidateResponse addNewCandidate(CandidateRequest candidateRequest) {
        if (candidateRepository.existsByNameAndSurName(candidateRequest.getName(), candidateRequest.getSurName())){
            throw new CandidateAlreadyExistsException(ERRORCODE.CANDIDATE_ALREADY_EXISTS_EXCEPTION);
        };

       Candidate candidate = FromRequestToEntity.fromCandidateRequestToEntityMapper(candidateRequest);
       // Candidate savedCandidate = candidateRepository.save(candidate);

        List<EducationRequest> educationsRequest = candidateRequest.getEducation();
        List<Education> educations = FromRequestToEntity.fromEducationListRequestToEducationEntityMapper(educationsRequest);
        educations.forEach(education -> {
            candidate.addEdu(education);
            education.setCandidate(candidate);}
        );

        List<TelNoRequest> telNoRequest = candidateRequest.getTelNo();
        List<TelNo> telNos = FromRequestToEntity.fromTelNoListRequestToTelNoEntityMapper(telNoRequest);
        telNos.forEach(telNo ->{
            candidate.addTelNo(telNo);
            telNo.setCandidate(candidate);});

        Candidate save = candidateRepository.save(candidate);
        return FromEntityToResponse.fromCandidateResponseToCandidateMapper(save);
    }

    public void deleteCandidate(Long candidateId) {
        candidateRepository.findById(candidateId).orElseThrow(()->new CandidateNotFoundException(ERRORCODE.CANDIDATE_NOT_FOUND_EXCEPTION));
    }
}
