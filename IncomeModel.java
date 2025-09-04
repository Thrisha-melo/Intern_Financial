package com.sampleProject.FinancialTracker.Model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IncomeModel {
    private Long id;
    private String title;
    private String description;
    private String category;
    private LocalDate date;
    private Integer amount;


}

