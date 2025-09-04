package com.sampleProject.FinancialTracker.Repository;

import com.sampleProject.FinancialTracker.Entity.Expense;

import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense,Long> {
    List<Expense> findByDateBetween(LocalDate StartDate, LocalDate EndDate);
    @Query("SELECT SUM(i.amount) from Expense i")
    double sumAllAmount();
    Optional<Expense> findTop1ByOrderByDate();
}
