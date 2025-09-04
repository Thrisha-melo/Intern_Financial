package com.sampleProject.FinancialTracker.Repository;

import com.sampleProject.FinancialTracker.Entity.Expense;
import com.sampleProject.FinancialTracker.Entity.Income;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
@Repository
public interface IncomeRepo extends JpaRepository<Income,Long> {

    List<Income> findByDateBetween(LocalDate StartDate, LocalDate EndDate);

    @Query("SELECT SUM(i.amount) from Income i")
    double sumAllAmount();
    Optional<Income> findTop1ByOrderByDate();
}
