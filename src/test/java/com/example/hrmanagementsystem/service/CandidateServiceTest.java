package com.example.hrmanagementsystem.service;

import com.example.hrmanagementsystem.exceptions.candidate.CandidateNotFoundException;
import com.example.hrmanagementsystem.model.entity.Candidate;
import com.example.hrmanagementsystem.model.enums.GENDER;
import com.example.hrmanagementsystem.model.response.candidate.CandidateResponseWithEducationsAndTelNo;
import com.example.hrmanagementsystem.repository.CandidateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class CandidateServiceTest {

    @Mock
    private CandidateRepository candidateRepository;

    @InjectMocks
    private CandidateService candidateService;

    private Candidate candidate1;
    private Candidate candidate2;

    @BeforeEach
    void setup() {
        candidate1 = Candidate.builder()
                .id(1L)
                .name("Aygun")
                .surName("Memmedova")
                .birthDate(LocalDate.of(1995, 5, 12))
                .birthPlace("Baki")
                .gender(GENDER.QADIN)
                .build();
        candidate2 = Candidate.builder()
                .id(2L)
                .name("Ramiz")
                .surName("Memmedov")
                .birthDate(LocalDate.of(1997, 5, 12))
                .birthPlace("Baki")
                .gender(GENDER.KISI)
                .build();
    }

    @Test
    void getAll() {
        //given
        var pageable = PageRequest.of(0, 2);
        var candidates = new PageImpl<>(Arrays.asList(candidate1, candidate2));
        when(candidateRepository.findAll(pageable))
                .thenReturn(candidates);


        //when
        Page<CandidateResponseWithEducationsAndTelNo> result = candidateService.findALl(pageable);


        //assert
        assertThat(result.getContent())
                .hasSize(2);
        assertThat(result.getContent().get(0).getName()).isEqualTo(candidate1.getName());
        assertThat(result.getContent().get(1).getName()).isEqualTo(candidate2.getName());

        verify(candidateRepository)
                .findAll(pageable);
    }


    @Test
    void shouldReturnWhenExist() {
        when(candidateRepository.findById(1L))
                .thenReturn(Optional.of(candidate1));

        Candidate candidate = candidateService.findCandidate(1L);

        assertThat(candidate)
                .isEqualTo(candidate1);
        verify(candidateRepository)
                .findById(1L);
    }

    @Test
    void shouldEmptyReturnWhenDoesNotExist() {
        when(candidateRepository.findById(3L))
                .thenReturn(Optional.empty());

        assertThrows(CandidateNotFoundException.class, () -> {
            candidateService.findCandidate(3L);
        });

        verify(candidateRepository)
                .findById(3L);

    }
}
