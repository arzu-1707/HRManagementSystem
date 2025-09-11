package com.example.hrmanagementsystem.controller;

import com.example.hrmanagementsystem.model.CommonResponse;
import com.example.hrmanagementsystem.model.request.CandidateRequest;
import com.example.hrmanagementsystem.model.request.NameSurnameRequest;
import com.example.hrmanagementsystem.model.response.CandidateResponse;
import com.example.hrmanagementsystem.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/candidate")
@RequiredArgsConstructor
public class CandidateController {
    private final CandidateService candidateService;

    @GetMapping
    public ResponseEntity<CommonResponse<Page<CandidateResponse>>> getALl(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ){
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<CandidateResponse> getAll = candidateService.findALl(page);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.success(getAll));
    }

    @PostMapping("/new")
    public ResponseEntity<CommonResponse<CandidateResponse>> addCandidate(
            @RequestBody CandidateRequest candidateRequest
            ){
        CandidateResponse candidateResponse = candidateService.addNewCandidate(candidateRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.success(candidateResponse));
    }

    @DeleteMapping("/{candidateId}")
    public ResponseEntity<CommonResponse<Void>> deleteCandidate(@PathVariable Long candidateId){
        candidateService.deleteCandidate(candidateId);
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
