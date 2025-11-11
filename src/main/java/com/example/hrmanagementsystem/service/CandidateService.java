package com.example.hrmanagementsystem.service;

import com.example.hrmanagementsystem.exceptions.education.EducationNotFoundException;
import com.example.hrmanagementsystem.mapper.FromEntityToResponse;
import com.example.hrmanagementsystem.mapper.FromRequestToEntity;
import com.example.hrmanagementsystem.exceptions.candidate.CandidateAlreadyExistsException;
import com.example.hrmanagementsystem.exceptions.candidate.CandidateNotFoundException;
import com.example.hrmanagementsystem.exceptions.candidate.CandidatesNotFoundException;
import com.example.hrmanagementsystem.model.entity.Candidate;
import com.example.hrmanagementsystem.model.entity.Education;
import com.example.hrmanagementsystem.model.entity.TelNo;
import com.example.hrmanagementsystem.model.enums.ERRORCODE;
import com.example.hrmanagementsystem.model.request.candidate.CandidateRequest;
import com.example.hrmanagementsystem.model.request.candidate.CandidateWithEducationTelNo;
import com.example.hrmanagementsystem.model.request.candidate.NameSurnameRequest;
import com.example.hrmanagementsystem.model.response.candidate.CandidateResponseWithEducationsAndTelNo;
import com.example.hrmanagementsystem.model.response.EducationResponse;
import com.example.hrmanagementsystem.model.response.telNO.TelNoResponse;
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

    public Page<CandidateResponseWithEducationsAndTelNo> findALl(Pageable page) {
        Page<Candidate> all = candidateRepository.findAll(page);
        if (all.isEmpty()){
            throw new CandidatesNotFoundException(ERRORCODE.CANDIDATES_NOT_FOUND_EXCEPTION);
        }
        return FromEntityToResponse.fromCandidateToPageCandidateResponseMapper(all);
    }

    public Candidate findCandidate(Long id){
        return candidateRepository.findById(id).orElseThrow(()-> new CandidateNotFoundException(ERRORCODE.CANDIDATE_NOT_FOUND_EXCEPTION) );
    }

    @Transactional
    public CandidateResponseWithEducationsAndTelNo addNewCandidate(CandidateWithEducationTelNo candidateWithEducationTelNo) {
        if (candidateRepository.existsCandidateByNameAndSurNameIgnoreCase(candidateWithEducationTelNo.getName(), candidateWithEducationTelNo.getSurName())){
            throw new CandidateAlreadyExistsException(ERRORCODE.CANDIDATE_ALREADY_EXISTS_EXCEPTION);
        };

       Candidate candidate = FromRequestToEntity.fromCandidateRequestToEntityMapper(candidateWithEducationTelNo);
        candidate.getEducations().forEach(e -> e.setCandidate(candidate));
        candidate.getTelNo().forEach(t -> t.setCandidate(candidate));

        Candidate savedCandidate = candidateRepository.save(candidate);


        return FromEntityToResponse.fromCandidateToCandidateResponseMapper(savedCandidate);
    }

    public void deleteCandidate(Long candidateId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException(ERRORCODE.CANDIDATE_NOT_FOUND_EXCEPTION));
        candidateRepository.delete(candidate);
    }

    public CandidateResponseWithEducationsAndTelNo updateCandidate(Long id, NameSurnameRequest nameSurnameRequest) {
        Candidate candidate = findCandidate(id);
        candidate.setName(nameSurnameRequest.getName());
        candidate.setSurName(nameSurnameRequest.getSurName());
        Candidate save = candidateRepository.save(candidate);
        return FromEntityToResponse.fromCandidateToCandidateResponseMapper(save);
    }
    


    public CandidateResponseWithEducationsAndTelNo findCandidateById(Long id) {
       return FromEntityToResponse.fromCandidateToCandidateResponseMapper(findCandidate(id));
    }

    public List<EducationResponse> findCandidateEducations(Long id) {
        Candidate candidate = findCandidate(id);
        List<Education> educations = candidate.getEducations();
        if (educations.isEmpty()){
            throw new EducationNotFoundException(ERRORCODE.CANDIDATE_NOT_FOUND_EXCEPTION);
        }
       return educations.stream()
                .map(FromEntityToResponse::fromEducationToEducationResponse).toList();
    }

    public List<TelNoResponse> findCandidateTelNos(Long id) {
        Candidate candidate = findCandidate(id);
        List<TelNo> telNos = candidate.getTelNo();
        return telNos.stream()
                .map(FromEntityToResponse::fromTelNoToTelNoResponse).toList();
    }

    public CandidateResponseWithEducationsAndTelNo addNewCandidate1(CandidateRequest candidateRequest) {
        Candidate candidate = FromRequestToEntity.fromCandidateRequestToEntityMapper(candidateRequest);

        List<Candidate> allByNameAndSurNameAndBirthDate =
                candidateRepository
                        .findAllByNameAndSurNameAndBirthDate(candidateRequest.getName(),
                                candidateRequest.getSurName(),
                                candidateRequest.getBirthDate());

        if (!allByNameAndSurNameAndBirthDate.isEmpty()){
            throw new CandidateAlreadyExistsException(ERRORCODE.CANDIDATE_ALREADY_EXISTS_EXCEPTION);
        }

        Candidate save = candidateRepository.save(candidate);

       return FromEntityToResponse.fromCandidateToCandidateResponseMapper(save);
    }
}
