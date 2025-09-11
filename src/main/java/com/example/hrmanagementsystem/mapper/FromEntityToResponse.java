package com.example.hrmanagementsystem.mapper;

import com.example.hrmanagementsystem.model.entity.Candidate;
import com.example.hrmanagementsystem.model.entity.Education;
import com.example.hrmanagementsystem.model.entity.TelNo;
import com.example.hrmanagementsystem.model.response.CandidateResponse;
import org.springframework.data.domain.Page;

public class FromEntityToResponse {
    public static Page<CandidateResponse> fromCandidateResponseToPageCandidateMapper(Page<Candidate> all) {

        return all
                .map(FromEntityToResponse::fromCandidateResponseToCandidateMapper);
    }


    public static CandidateResponse fromCandidateResponseToCandidateMapper(Candidate candidate){
        return CandidateResponse.builder()
                .id(candidate.getId())
                .name(candidate.getName())
                .surName(candidate.getSurName())
                .gender(candidate.getGender())
                .birthDate(candidate.getBirthDate())
                .birthPlace(candidate.getBirthPlace())
                .educations(candidate.getEducations().stream().map(
                        Education::getId
                ).toList())
                .tel(candidate.getTelNo().stream().map(TelNo::getNumber).toList()).build();
    }
}
