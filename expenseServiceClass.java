package com.sampleProject.FinancialTracker.Service;

import com.sampleProject.FinancialTracker.Entity.Expense;
import com.sampleProject.FinancialTracker.Model.ExpenseModel;
import com.sampleProject.FinancialTracker.Repository.ExpenseRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class expenseServiceClass implements ExpenseService {

    private final ExpenseRepo repo;

    @Override
    public Expense postexpense(ExpenseModel emp) {
        Expense exp = new Expense();
        exp.setTitle(emp.getTitle());
        exp.setCategory(emp.getCategory());
        exp.setAmount(emp.getAmount());
        exp.setDate(emp.getDate());
        exp.setDescription(emp.getDescription());
        exp.setUpdatedAt(System.currentTimeMillis()); // ✅ set timestamp
        return repo.save(exp);
    }

    public List<Expense> getAllExpenses() {
        return repo.findAll().stream()
                .sorted(Comparator.comparing(
                        Expense::getDate,
                        Comparator.nullsLast(Comparator.reverseOrder())
                ))
                .collect(Collectors.toList());
    }

    public Expense getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found the details with id: " + id));
    }

    private Expense saveOrUpdateExpense(Expense expense, ExpenseModel emp) {
        if (emp.getAmount() != null) expense.setAmount(emp.getAmount());
        if (emp.getDescription() != null) expense.setDescription(emp.getDescription());
        if (emp.getCategory() != null) expense.setCategory(emp.getCategory());
        if (emp.getDate() != null) expense.setDate(emp.getDate());
        if (emp.getTitle() != null) expense.setTitle(emp.getTitle());
        expense.setUpdatedAt(System.currentTimeMillis()); // ✅ refresh timestamp
        return repo.save(expense);
    }

    public Expense UpdateExpense(Long id, ExpenseModel emp) {
        Optional<Expense> ops = repo.findById(id);
        if (ops.isPresent()) {
            return saveOrUpdateExpense(ops.get(), emp);
        } else {
            throw new EntityNotFoundException("ENTITY NOT FOUND WITH ID " + id);
        }
    }

    public void DeleteById(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            throw new EntityNotFoundException("ENTITY NOT FOUND WITH ID " + id);
        }
    }
}
