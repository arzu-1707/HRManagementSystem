package com.example.hrmanagementsystem.repository;

import com.example.hrmanagementsystem.model.entity.TelNo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelNoRepository extends JpaRepository<TelNo, Long> {
}
