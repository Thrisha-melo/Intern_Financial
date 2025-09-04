package com.sampleProject.FinancialTracker.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;

@Entity
@Data
public class Expense {
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
