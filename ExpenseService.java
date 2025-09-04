package com.sampleProject.FinancialTracker.Service;

import com.sampleProject.FinancialTracker.Entity.Expense;
import com.sampleProject.FinancialTracker.Model.ExpenseModel;

import java.util.List;

public interface ExpenseService {
    Expense postexpense( ExpenseModel emp);
    List<Expense> getAllExpenses();
    Expense getById(Long id);
    Expense UpdateExpense(Long id,ExpenseModel emp);
    void DeleteById(Long id);
}
