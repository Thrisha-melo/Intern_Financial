package com.sampleProject.FinancialTracker.Controller;

import com.sampleProject.FinancialTracker.Entity.Income;
import com.sampleProject.FinancialTracker.Model.ExpenseModel;
import com.sampleProject.FinancialTracker.Model.IncomeModel;
import com.sampleProject.FinancialTracker.Service.IncomeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/income")
public class IncomeController {

    private final IncomeService incomeService;
    public IncomeController(IncomeService incomeService)
    {
        this.incomeService
                =incomeService;
    }
    @PostMapping
    public ResponseEntity<?>postIncome(@RequestBody IncomeModel imp)
    {
        Income ic= incomeService.postIncome(imp);
        if(ic!=null)
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(ic);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping
    public ResponseEntity<?>getIncome()
    {
        return ResponseEntity.ok(incomeService.getIncome());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id)
    {
        try
        {
            return ResponseEntity.ok(incomeService.getById(id));
        }catch (EntityNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("SOMETHING WENT WRONG");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> UpdateIncome(@PathVariable Long id,@RequestBody IncomeModel imp)
    {
        try{
            return ResponseEntity.ok(incomeService.UpdateIncome(id,imp));
        }
        catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("SOMETHING WENT WRONG");
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> DeleteById(@PathVariable Long id)
    {
        try{
            incomeService.DeleteById(id);
            return ResponseEntity.ok(null);
        }
        catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("SOMETHING WENT WRONG");
        }

    }

}
