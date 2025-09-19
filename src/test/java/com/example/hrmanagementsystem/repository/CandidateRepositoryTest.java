package com.example.hrmanagementsystem.repository;

import com.example.hrmanagementsystem.model.entity.Candidate;
import com.example.hrmanagementsystem.model.enums.GENDER;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;


import java.time.LocalDate;

@DataJpaTest
@ActiveProfiles("test")
public class CandidateRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CandidateRepository candidateRepository;

    private Candidate candidate1;
    private Candidate candidate2;
    private Candidate candidate3;

    @BeforeEach
    void setup() {
        candidate1 = Candidate.builder()
                .name("Aygun")
                .surName("Memmedova")
                .birthDate(LocalDate.of(1995, 5, 12))
                .birthPlace("Baki")
                .gender(GENDER.QADIN)
                .build();
        candidate2 = Candidate.builder()
                .name("Ramiz")
                .surName("Memmedov")
                .birthDate(LocalDate.of(1997, 5, 12))
                .birthPlace("Baki")
                .gender(GENDER.KISI)
                .build();

        candidate3 = Candidate.builder()
                .name("Aysel")
                .surName("Yahyayeva")
                .birthDate(LocalDate.of(1990, 5, 12))
                .birthPlace("Sumqayit")
                .gender(GENDER.QADIN)
                .build();
    }

    private void persistCandidateTest(){
        testEntityManager.persist(candidate1);
        testEntityManager.persist(candidate2);
        testEntityManager.persist(candidate3);
        testEntityManager.flush();
    }

    @ParameterizedTest
    @DisplayName("Should return Candidate by name and surname (ignore case)")
    @CsvSource({
            "Aygun, Memmedova",
            "AyGun, MeMMedova",
            "aygun, memmedova"
    })
    void existsCandidateByNameAndSurNameIgnoreCase(String name, String surName){
        //given
        persistCandidateTest();

        //when
        boolean exists = candidateRepository.existsCandidateByNameAndSurNameIgnoreCase(name, surName);

        //then
        assertThat(exists);
    }
}
