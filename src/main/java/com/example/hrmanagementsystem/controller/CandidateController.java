package com.example.hrmanagementsystem.controller;

import com.example.hrmanagementsystem.model.CommonResponse;
import com.example.hrmanagementsystem.model.request.CandidateRequest;
import com.example.hrmanagementsystem.model.request.NameSurnameRequest;
import com.example.hrmanagementsystem.model.response.CandidateResponse;
import com.example.hrmanagementsystem.model.response.EducationResponse;
import com.example.hrmanagementsystem.model.response.telNO.TelNoResponse;
import com.example.hrmanagementsystem.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/candidate")
@RequiredArgsConstructor
public class CandidateController {
    private final CandidateService candidateService;

    @GetMapping("/all")
    public ResponseEntity<CommonResponse<Page<CandidateResponse>>> getALl(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ){
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<CandidateResponse> getAll = candidateService.findALl(page);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.success(getAll));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<CandidateResponse>> getCandidateById(@PathVariable Long id){
        CandidateResponse candidateResponse = candidateService.findCandidateById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.success("Operation is successfully", candidateResponse));
    }

    @GetMapping("/{id}/educations")
    public ResponseEntity<CommonResponse<List<EducationResponse>>> getEduInCandidate(@PathVariable Long id){
        List<EducationResponse> educationResponses = candidateService.findCandidateEducations(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.success(educationResponses));
    }

    @GetMapping("/{id}/tel-no")
    public ResponseEntity<CommonResponse<List<TelNoResponse>>> getTelNoInCandidate(@PathVariable Long id){
        List<TelNoResponse> telNo = candidateService.findCandidateTelNos(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.success(telNo));
    }

    @PostMapping("/new")
    public ResponseEntity<CommonResponse<CandidateResponse>> addCandidate(
            @RequestBody CandidateRequest candidateRequest
            ){
        CandidateResponse candidateResponse = candidateService.addNewCandidate(candidateRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.success(candidateResponse));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<Void>> deleteCandidate(@PathVariable Long id){
        candidateService.deleteCandidate(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.success("Operation is successfully",null));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommonResponse<CandidateResponse>> updateCandidate(@PathVariable Long id,
                                                                             @RequestBody NameSurnameRequest nameSurnameRequest){
        CandidateResponse candidateResponse = candidateService.updateCandidate(id, nameSurnameRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.success("Operation is successfully", candidateResponse));
    }
}
