package com.sampleProject.FinancialTracker.Model;

import com.sampleProject.FinancialTracker.Entity.Expense;
import com.sampleProject.FinancialTracker.Entity.Income;
import lombok.Data;

import java.util.List;
@Data
public class GraphModel {
    private List<Expense> expenseList;
    private List<Income> incomeList;

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    public List<Income> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(List<Income> incomeList) {
        this.incomeList = incomeList;
    }
}
