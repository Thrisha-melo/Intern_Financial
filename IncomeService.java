package com.sampleProject.FinancialTracker.Service;

import com.sampleProject.FinancialTracker.Entity.Income;
import com.sampleProject.FinancialTracker.Model.IncomeModel;

import java.util.List;

public interface IncomeService {
    Income postIncome(IncomeModel imp);
    List<Income> getIncome();
    Income getById(Long id);
    Income UpdateIncome(Long id,IncomeModel imp);
    void DeleteById(Long id);
}
