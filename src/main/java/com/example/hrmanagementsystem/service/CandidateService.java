package com.example.hrmanagementsystem.service;

import com.example.hrmanagementsystem.Mapper.FromEntityToResponseMapper;
import com.example.hrmanagementsystem.model.entity.Candidate;
import com.example.hrmanagementsystem.model.response.CandidateResponse;
import com.example.hrmanagementsystem.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateService {
    private final CandidateRepository candidateRepository;

    public Page<CandidateResponse> findALl(Pageable page) {
        Page<Candidate> all = candidateRepository.findAll(page);
        return FromEntityToResponseMapper.ToCandidateMapper(all);
    }
}
