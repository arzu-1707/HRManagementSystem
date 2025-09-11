package com.example.hrmanagementsystem.service;

import com.example.hrmanagementsystem.mapper.FromEntityToResponse;
import com.example.hrmanagementsystem.mapper.FromRequestToEntity;
import com.example.hrmanagementsystem.exceptions.candidate.CandidateAlreadyExistsException;
import com.example.hrmanagementsystem.exceptions.candidate.CandidateNotFoundException;
import com.example.hrmanagementsystem.exceptions.candidate.CandidatesNotFoundException;
import com.example.hrmanagementsystem.model.entity.Candidate;
import com.example.hrmanagementsystem.model.enums.ERRORCODE;
import com.example.hrmanagementsystem.model.request.CandidateRequest;
import com.example.hrmanagementsystem.model.request.NameSurnameRequest;
import com.example.hrmanagementsystem.model.response.CandidateResponse;
import com.example.hrmanagementsystem.repository.CandidateRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        if (candidateRepository.existsCandidateByNameAndSurNameIgnoreCase(candidateRequest.getName(), candidateRequest.getSurName())){
            throw new CandidateAlreadyExistsException(ERRORCODE.CANDIDATE_ALREADY_EXISTS_EXCEPTION);
        };

       Candidate candidate = FromRequestToEntity.fromCandidateRequestToEntityMapper(candidateRequest);
        candidate.getEducations().forEach(e -> e.setCandidate(candidate));
        candidate.getTelNo().forEach(t -> t.setCandidate(candidate));

        Candidate savedCandidate = candidateRepository.save(candidate);


        return FromEntityToResponse.fromCandidateResponseToCandidateMapper(savedCandidate);
    }

    public void deleteCandidate(Long candidateId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException(ERRORCODE.CANDIDATE_NOT_FOUND_EXCEPTION));
        candidateRepository.delete(candidate);
    }

    public CandidateResponse updateCandidate(Long id, NameSurnameRequest nameSurnameRequest) {
        Candidate candidate = findCandidate(id);
        candidate.setName(nameSurnameRequest.getName());
        candidate.setSurName(nameSurnameRequest.getSurName());
        Candidate save = candidateRepository.save(candidate);
        return FromEntityToResponse.fromCandidateResponseToCandidateMapper(save);
    }
    
    public Candidate findCandidate(Long id){
       return candidateRepository.findById(id).orElseThrow(()-> new CandidateNotFoundException(ERRORCODE.CANDIDATE_NOT_FOUND_EXCEPTION) );
    }
}
