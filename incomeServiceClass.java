package com.sampleProject.FinancialTracker.Service;

import com.sampleProject.FinancialTracker.Entity.Income;
import com.sampleProject.FinancialTracker.Model.IncomeModel;
import com.sampleProject.FinancialTracker.Repository.IncomeRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class incomeServiceClass implements IncomeService {

    private final IncomeRepo incomeRepo;

    public Income postIncome(IncomeModel imp) {
        Income im = new Income();
        im.setAmount(imp.getAmount());
        im.setDate(imp.getDate());
        im.setCategory(imp.getCategory());
        im.setDescription(imp.getDescription());
        im.setTitle(imp.getTitle());
        im.setUpdatedAt(System.currentTimeMillis());
        return incomeRepo.save(im);
    }

    public List<Income> getIncome() {
        return incomeRepo.findAll().stream()
                .sorted(Comparator.comparing(
                        Income::getDate,
                        Comparator.nullsLast(Comparator.reverseOrder())
                ))
                .collect(Collectors.toList());
    }

    public Income getById(Long id) {
        return incomeRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found the details with id:" + id));
    }

    private Income saveOrUpdate(Income income, IncomeModel imp) {
        if (imp.getAmount() != null) income.setAmount(imp.getAmount());
        if (imp.getDescription() != null) income.setDescription(imp.getDescription());
        if (imp.getCategory() != null) income.setCategory(imp.getCategory());
        if (imp.getDate() != null) income.setDate(imp.getDate());
        if (imp.getTitle() != null) income.setTitle(imp.getTitle());
        income.setUpdatedAt(System.currentTimeMillis());
        return incomeRepo.save(income);
    }

    public Income UpdateIncome(Long id, IncomeModel imp) {
        return incomeRepo.findById(id)
                .map(income -> saveOrUpdate(income, imp))
                .orElseThrow(() -> new EntityNotFoundException("ENTITY NOT FOUND WITH ID " + id));
    }

    public void DeleteById(Long id) {
        if (incomeRepo.existsById(id)) {
            incomeRepo.deleteById(id);
        } else {
            throw new EntityNotFoundException("ENTITY NOT FOUND WITH ID " + id);
        }
    }
}
