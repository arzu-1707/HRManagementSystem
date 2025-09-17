package com.example.hrmanagementsystem.service;

import com.example.hrmanagementsystem.exceptions.candidate.CandidateNotFoundException;
import com.example.hrmanagementsystem.mapper.FromEntityToResponse;
import com.example.hrmanagementsystem.mapper.FromRequestToEntity;
import com.example.hrmanagementsystem.model.entity.Candidate;
import com.example.hrmanagementsystem.model.entity.TelNo;
import com.example.hrmanagementsystem.model.enums.ERRORCODE;
import com.example.hrmanagementsystem.model.request.telNo.TelNoListRequest;
import com.example.hrmanagementsystem.model.response.CandidateResponse;
import com.example.hrmanagementsystem.repository.CandidateRepository;
import com.example.hrmanagementsystem.repository.TelNoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TelNoService {

    private final TelNoRepository telNoRepository;
    private final CandidateRepository candidateRepository;

    public TelNo save(TelNo telNo){
        return telNoRepository.save(telNo);
    }

    public CandidateResponse addTelNoInCandidate(Long id, TelNoListRequest telNoRequest) {
        Candidate candidate = candidateRepository.findById(id).orElseThrow(() -> new CandidateNotFoundException(ERRORCODE.CANDIDATE_NOT_FOUND_EXCEPTION));

        List<TelNo> telNos = FromRequestToEntity.fromTelNoListRequestToTelNoEntityMapper(telNoRequest.getTelNoRequests());
        if(candidate.getTelNo()== null){
            candidate.setTelNo(new ArrayList<>());
        }
        candidate.getTelNo().addAll(telNos);

        candidate.getTelNo()
                .forEach(telNo -> telNo.setCandidate(candidate));

        Candidate save = candidateRepository.save(candidate);

        return FromEntityToResponse.fromCandidateToCandidateResponseMapper(save);


    }
}
