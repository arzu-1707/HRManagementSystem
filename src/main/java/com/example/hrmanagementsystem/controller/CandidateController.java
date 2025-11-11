package com.example.hrmanagementsystem.controller;

import com.example.hrmanagementsystem.model.CommonResponse;
import com.example.hrmanagementsystem.model.request.candidate.CandidateRequest;
import com.example.hrmanagementsystem.model.request.candidate.CandidateWithEducationTelNo;
import com.example.hrmanagementsystem.model.request.candidate.NameSurnameRequest;
import com.example.hrmanagementsystem.model.request.education.EducationListRequest;
import com.example.hrmanagementsystem.model.request.telNo.TelNoListRequest;
import com.example.hrmanagementsystem.model.response.candidate.CandidateResponseWithEducationsAndTelNo;
import com.example.hrmanagementsystem.model.response.EducationResponse;
import com.example.hrmanagementsystem.model.response.telNO.TelNoResponse;
import com.example.hrmanagementsystem.service.CandidateService;
import com.example.hrmanagementsystem.service.EducationService;
import com.example.hrmanagementsystem.service.TelNoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/candidate")
@RequiredArgsConstructor
public class CandidateController {
    private final CandidateService candidateService;
    private final EducationService educationService;
    private final TelNoService telNoService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/all")
    public ResponseEntity<CommonResponse<Page<CandidateResponseWithEducationsAndTelNo>>> getALl(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ){
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<CandidateResponseWithEducationsAndTelNo> getAll = candidateService.findALl(page);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.success(getAll));
    }

    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<CandidateResponseWithEducationsAndTelNo>> getCandidateById(@PathVariable Long id){
        CandidateResponseWithEducationsAndTelNo candidateResponseWithEducationsAndTelNo = candidateService.findCandidateById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.success("Operation is successfully", candidateResponseWithEducationsAndTelNo));
    }

    @GetMapping("/{id}/educations")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ResponseEntity<CommonResponse<List<EducationResponse>>> getEduInCandidate(@PathVariable Long id){
        List<EducationResponse> educationResponses = candidateService.findCandidateEducations(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.success(educationResponses));
    }

    @GetMapping("/{id}/tel-no")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ResponseEntity<CommonResponse<List<TelNoResponse>>> getTelNoInCandidate(@PathVariable Long id){
        List<TelNoResponse> telNo = candidateService.findCandidateTelNos(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.success(telNo));
    }

    @PostMapping("/new-with-edu-tel")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<CandidateResponseWithEducationsAndTelNo>> addCandidate(
            @RequestBody CandidateWithEducationTelNo candidateWithEducationTelNo
            ){
        CandidateResponseWithEducationsAndTelNo candidateResponseWithEducationsAndTelNo = candidateService.addNewCandidate(candidateWithEducationTelNo);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.success(candidateResponseWithEducationsAndTelNo));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<CandidateResponseWithEducationsAndTelNo>> addCandidate(
            @RequestBody CandidateRequest candidateRequest
    ){
        CandidateResponseWithEducationsAndTelNo candidateResponseWithEducationsAndTelNo = candidateService.addNewCandidate1(candidateRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.success(candidateResponseWithEducationsAndTelNo));
    }

    @PostMapping("/{id}/add-edu")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<CandidateResponseWithEducationsAndTelNo>> addEducation(
            @PathVariable Long id,
            @RequestBody EducationListRequest educations){

        CandidateResponseWithEducationsAndTelNo candidateResponseWithEducationsAndTelNo = educationService.addEduInCandidate(id, educations);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.success(candidateResponseWithEducationsAndTelNo));
    }


    @PostMapping("/{id}/add-tel")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<CandidateResponseWithEducationsAndTelNo>> addTelNo(
            @PathVariable Long id,
            @RequestBody TelNoListRequest telNoRequest
            ){
        CandidateResponseWithEducationsAndTelNo candidateResponseWithEducationsAndTelNo = telNoService.addTelNoInCandidate(id, telNoRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.success(candidateResponseWithEducationsAndTelNo));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<Void>> deleteCandidate(@PathVariable Long id){
        candidateService.deleteCandidate(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.success("Operation is successfully",null));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<CandidateResponseWithEducationsAndTelNo>> updateCandidate(@PathVariable Long id,
                                                                                                   @RequestBody NameSurnameRequest nameSurnameRequest){
        CandidateResponseWithEducationsAndTelNo candidateResponseWithEducationsAndTelNo = candidateService.updateCandidate(id, nameSurnameRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.success("Operation is successfully", candidateResponseWithEducationsAndTelNo));
    }


}
