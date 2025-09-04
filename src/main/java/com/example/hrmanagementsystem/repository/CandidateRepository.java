package com.example.hrmanagementsystem.repository;

import com.example.hrmanagementsystem.model.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    boolean existsByNameAndSurName(String name, String surName);
}
