package com.sampleProject.FinancialTracker.Model;

import com.sampleProject.FinancialTracker.Entity.Expense;
import com.sampleProject.FinancialTracker.Entity.Income;
import lombok.Data;

@Data
public class StatsModel {
    private double income;
    private double expense;
    private Income latestIncome;
    private Expense latestExpense;
    private double balance;
    private double minIncome;
    private double maxIncome;
    private double minExpense;
    private double maxExpense;


}
