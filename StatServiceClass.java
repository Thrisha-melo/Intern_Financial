package com.sampleProject.FinancialTracker.Service.Statistics;

import com.sampleProject.FinancialTracker.Entity.Expense;
import com.sampleProject.FinancialTracker.Entity.Income;
import com.sampleProject.FinancialTracker.Model.GraphModel;
import com.sampleProject.FinancialTracker.Model.StatsModel;
import com.sampleProject.FinancialTracker.Repository.ExpenseRepo;
import com.sampleProject.FinancialTracker.Repository.IncomeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
public class StatServiceClass implements StatService {

    @Autowired
    private final IncomeRepo incomeRepo;

    @Autowired
    private final ExpenseRepo expenseRepo;

    public StatServiceClass(IncomeRepo incomeRepo, ExpenseRepo expenseRepo) {
        this.incomeRepo = incomeRepo;
        this.expenseRepo = expenseRepo;
    }

    public GraphModel getChartData() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(27);
        GraphModel gm = new GraphModel();
        gm.setExpenseList(expenseRepo.findByDateBetween(startDate, endDate));
        gm.setIncomeList(incomeRepo.findByDateBetween(startDate, endDate));
        return gm;
    }

    public StatsModel getStat() {
        double totalIncome = incomeRepo.sumAllAmount();
        double totalExpense = expenseRepo.sumAllAmount();

        Optional<Income> incomeOptional = incomeRepo.findTop1ByOrderByDate();
        Optional<Expense> expenseOptional = expenseRepo.findTop1ByOrderByDate();

        StatsModel sm = new StatsModel();
        sm.setExpense(totalExpense);
        sm.setIncome(totalIncome);
        incomeOptional.ifPresent(sm::setLatestIncome);
        expenseOptional.ifPresent(sm::setLatestExpense);
        sm.setBalance(totalIncome - totalExpense);

        List<Income> incomeList = incomeRepo.findAll();
        List<Expense> expenseList = expenseRepo.findAll();

        // Calculate min and max values
        OptionalDouble minIncome = incomeList.stream().mapToDouble(Income::getAmount).min();
        OptionalDouble maxIncome = incomeList.stream().mapToDouble(Income::getAmount).max();
        OptionalDouble minExpense = expenseList.stream().mapToDouble(Expense::getAmount).min();
        OptionalDouble maxExpense = expenseList.stream().mapToDouble(Expense::getAmount).max();

        // Set min and max values in StatsModel
        sm.setMinIncome(minIncome.isPresent() ? minIncome.getAsDouble() : 0.0);
        sm.setMaxIncome(maxIncome.isPresent() ? maxIncome.getAsDouble() : 0.0);
        sm.setMinExpense(minExpense.isPresent() ? minExpense.getAsDouble() : 0.0);
        sm.setMaxExpense(maxExpense.isPresent() ? maxExpense.getAsDouble() : 0.0);

        return sm;
    }
}
