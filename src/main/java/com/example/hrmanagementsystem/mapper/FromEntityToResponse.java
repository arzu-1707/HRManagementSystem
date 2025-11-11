package com.example.hrmanagementsystem.mapper;

import com.example.hrmanagementsystem.model.entity.AppUser;
import com.example.hrmanagementsystem.model.entity.Candidate;
import com.example.hrmanagementsystem.model.entity.Education;
import com.example.hrmanagementsystem.model.entity.TelNo;
import com.example.hrmanagementsystem.model.response.candidate.CandidateResponseWithEducationsAndTelNo;
import com.example.hrmanagementsystem.model.response.EducationResponse;
import com.example.hrmanagementsystem.model.response.telNO.TelNoResponse;
import com.example.hrmanagementsystem.model.response.user.UserResponse;
import org.springframework.data.domain.Page;

public class FromEntityToResponse {
    public static Page<CandidateResponseWithEducationsAndTelNo> fromCandidateToPageCandidateResponseMapper(Page<Candidate> all) {

        return all
                .map(FromEntityToResponse::fromCandidateToCandidateResponseMapper);
    }


    public static CandidateResponseWithEducationsAndTelNo fromCandidateToCandidateResponseMapper(Candidate candidate){
        return CandidateResponseWithEducationsAndTelNo.builder()
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

    public static EducationResponse fromEducationToEducationResponse(Education education) {
        return EducationResponse.builder()
                .id(education.getId())
                .degree(education.getDegree())
                .name(education.getName())
                .profession(education.getProfession())
                .build();
    }

    public static TelNoResponse fromTelNoToTelNoResponse(TelNo telNo) {
        return TelNoResponse.builder()
                .id(telNo.getId())
                .number(telNo.getNumber())
                .build();
    }

    public static UserResponse fromUserEntityToUserResponse(AppUser savedUser) {
        return UserResponse.builder()
                .userName(savedUser.getUserName())
                .id(savedUser.getId())
                .build();
    }
}
