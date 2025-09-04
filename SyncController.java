package com.sampleProject.FinancialTracker.Controller;

import com.sampleProject.FinancialTracker.Entity.Expense;
import com.sampleProject.FinancialTracker.Entity.Income;
import com.sampleProject.FinancialTracker.Repository.ExpenseRepo;
import com.sampleProject.FinancialTracker.Repository.IncomeRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/sync")
public class SyncController {
    private final ExpenseRepo expenseRepo;
    private final IncomeRepo incomeRepo;

    public SyncController(ExpenseRepo expenseRepo, IncomeRepo incomeRepo) {
        this.expenseRepo = expenseRepo;
        this.incomeRepo = incomeRepo;
    }

    @PostMapping("/expenses")
    public List<Expense> syncExpenses(@RequestBody List<Expense> clientExpenses) {
        List<Expense> updated = new ArrayList<>();

        for (Expense clientExp : clientExpenses) {
            Expense existing = expenseRepo.findById(clientExp.getId()).orElse(null);

            if (existing == null) {
                clientExp.setUpdatedAt(System.currentTimeMillis());
                updated.add(expenseRepo.save(clientExp));
            } else {
                // Conflict resolution: Last Write Wins
                if (clientExp.getUpdatedAt() >= existing.getUpdatedAt()) {
                    existing.setTitle(clientExp.getTitle());
                    existing.setCategory(clientExp.getCategory());
                    existing.setAmount(clientExp.getAmount());
                    existing.setDate(clientExp.getDate());
                    existing.setDescription(clientExp.getDescription());
                    existing.setUpdatedAt(System.currentTimeMillis());
                    updated.add(expenseRepo.save(existing));
                } else {
                    updated.add(existing);
                }
            }
        }
        return updated;
    }

    @PostMapping("/incomes")
    public List<Income> syncIncomes(@RequestBody List<Income> clientIncomes) {
        List<Income> updated = new ArrayList<>();

        for (Income clientInc : clientIncomes) {
            Income existing = incomeRepo.findById(clientInc.getId()).orElse(null);

            if (existing == null) {
                clientInc.setUpdatedAt(System.currentTimeMillis());
                updated.add(incomeRepo.save(clientInc));
            } else {
                if (clientInc.getUpdatedAt() >= existing.getUpdatedAt()) {
                    existing.setTitle(clientInc.getTitle());
                    existing.setCategory(clientInc.getCategory());
                    existing.setAmount(clientInc.getAmount());
                    existing.setDate(clientInc.getDate());
                    existing.setDescription(clientInc.getDescription());
                    existing.setUpdatedAt(System.currentTimeMillis());
                    updated.add(incomeRepo.save(existing));
                } else {
                    updated.add(existing);
                }
            }
        }
        return updated;
    }
}
