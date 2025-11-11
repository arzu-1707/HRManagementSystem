package com.example.hrmanagementsystem.mapper;

import com.example.hrmanagementsystem.model.entity.*;
import com.example.hrmanagementsystem.model.request.candidate.CandidateRequest;
import com.example.hrmanagementsystem.model.request.candidate.CandidateWithEducationTelNo;
import com.example.hrmanagementsystem.model.request.education.EducationRequest;
import com.example.hrmanagementsystem.model.request.telNo.TelNoRequest;
import com.example.hrmanagementsystem.model.request.user.AppUserRequest;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class FromRequestToEntity {
    public static Candidate fromCandidateRequestToEntityMapper(CandidateWithEducationTelNo candidateWithEducationTelNo) {
        return Candidate.builder()
                .name(candidateWithEducationTelNo.getName())
                .surName(candidateWithEducationTelNo.getSurName())
                .gender(candidateWithEducationTelNo.getGender())
                .birthDate(candidateWithEducationTelNo.getBirthDate())
                .birthPlace(candidateWithEducationTelNo.getBirthPlace())
                .educations(fromEducationListRequestToEducationEntityMapper(candidateWithEducationTelNo.getEducation()))
                .telNo(fromTelNoRequestToTelNoEntityMapper(candidateWithEducationTelNo.getTelNo()))
                .build();
    }

    public static Candidate fromCandidateRequestToEntityMapper(CandidateRequest candidateRequest){
        return Candidate.builder()
                .name(candidateRequest.getName())
                .surName(candidateRequest.getSurName())
                .gender(candidateRequest.getGender())
                .birthDate(candidateRequest.getBirthDate())
                .birthPlace(candidateRequest.getBirthPlace())
                .build();
    }

    private static List<TelNo> fromTelNoRequestToTelNoEntityMapper(List<TelNoRequest> telNo) {
        return telNo.stream()
                .map(telNoRequest -> TelNo.builder()
                        .number(telNoRequest.getNumber()).build())
                .collect(Collectors.toList());
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
                        .build()).collect(Collectors.toList());
    }

    public static List<TelNo> fromTelNoListRequestToTelNoEntityMapper(List<TelNoRequest> telNos) {
       return telNos.stream()
                .map(telNoRequest -> TelNo.builder()
                        .number(telNoRequest.getNumber())
                        .build()).toList();
    }

    public static AppUser fromAppUserRequestToEntity(AppUserRequest appUserRequest) {
        return AppUser.builder()
                .userName(appUserRequest.getUserName())
                .role(new HashSet<>())
                .build();
    }
}
