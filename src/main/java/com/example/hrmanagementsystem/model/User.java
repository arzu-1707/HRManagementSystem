package com.example.hrmanagementsystem.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class User {

    private Long id;

    private String name;

    private String surName;

    private LocalDate birthDate;

    private BirthPlace birthPlace;

    private Education education;

    private TelNo telNo;

    //sekil de qoymagi arasdir!!!!!!

}
