package com.example.hrmanagementsystem.Mapper;

import com.example.hrmanagementsystem.model.entity.Candidate;
import com.example.hrmanagementsystem.model.entity.Education;
import com.example.hrmanagementsystem.model.entity.TelNo;
import com.example.hrmanagementsystem.model.request.CandidateRequest;
import com.example.hrmanagementsystem.model.request.EducationRequest;
import com.example.hrmanagementsystem.model.request.TelNoRequest;

import java.util.List;

public class FromRequestToEntityM {
    public static Candidate fromCandidateRequestToEntityMapper(CandidateRequest candidateRequest) {
        return Candidate.builder()
                .name(candidateRequest.getName())
                .surName(candidateRequest.getSurName())
                .gender(candidateRequest.getGender())
                .birthDate(candidateRequest.getBirthDate())
                .birthPlace(candidateRequest.getBirthPlace())
                .education(fromEducationListRequestToEducationEntityMapper(candidateRequest.getEducation()))
                .telNo(fromTelNoRequestToTelNoEntityMapper(candidateRequest.getTelNo()))
                .build();
    }

    private static List<TelNo> fromTelNoRequestToTelNoEntityMapper(List<TelNoRequest> telNo) {
        return telNo.stream()
                .map(telNoRequest -> TelNo.builder()
                        .number(telNoRequest.getNumber()).build())
                .toList();
    }

    public static List<Education> fromEducationListRequestToEducationEntityMapper(List<EducationRequest> educations)
    {
        return educations.stream()
                .map(educationRequest -> Education.builder()
                        .name(educationRequest.getName())
                        .degree(educationRequest.getDegree())
                        .profession(educationRequest.getProfession())
                        .startEdu(educationRequest.getStartEdu())
                        .endEdu(educationRequest.getEndEdu())
                        .build()).toList();
    }

    public static List<TelNo> fromTelNoListRequestToTelNoEntityMapper(List<TelNoRequest> telNos) {
       return telNos.stream()
                .map(telNoRequest -> TelNo.builder()
                        .number(telNoRequest.getNumber())
                        .build()).toList();
    }
}
