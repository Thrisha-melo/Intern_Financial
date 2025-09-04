package com.sampleProject.FinancialTracker.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name="income")
@Data
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String category;
    private LocalDate date;
    private Integer amount;
    @Column(name = "updated_at")
    private Long updatedAt;
}

