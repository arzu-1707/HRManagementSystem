package com.example.hrmanagementsystem.Mapper;

import com.example.hrmanagementsystem.model.entity.Candidate;
import com.example.hrmanagementsystem.model.entity.Education;
import com.example.hrmanagementsystem.model.response.CandidateResponse;
import org.springframework.data.domain.Page;

public class FromEntityToResponseMapper {
    public static Page<CandidateResponse> ToCandidateMapper(Page<Candidate> all) {

        return all
                .map(candidate -> CandidateResponse.builder()
                        .id(candidate.getId())
                        .name(candidate.getName())
                        .surName(candidate.getSurName())
                        .gender(candidate.getGender())
                        .birthDate(candidate.getBirthDate())
                        .birthPlace(candidate.getBirthPlace())
                        .educations(candidate.getEducation().stream().map(
                                Education::getId
                        ).toList()).build());
    }
}
