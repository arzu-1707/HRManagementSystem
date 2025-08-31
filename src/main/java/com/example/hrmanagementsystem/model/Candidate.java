package com.example.hrmanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private String surName;

    private String gender;

    private LocalDate birthDate;

    private String birthPlace;

    @ManyToMany
    @JoinTable(
            name = "candidate_edu",
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "education_id")
    )
    private List<Education> education;

    @ManyToMany
    @JoinTable(
            name = "candidate_tel",
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "tel_id")
    )
    private List<TelNo> telNo;
}
