package com.sampleProject.FinancialTracker.Controller;

import com.sampleProject.FinancialTracker.Entity.Expense;
import com.sampleProject.FinancialTracker.Model.ExpenseModel;
import com.sampleProject.FinancialTracker.Service.ExpenseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expense")

public class ExpenseController {


    private final ExpenseService expo;

    public ExpenseController(ExpenseService expo) {
        this.expo = expo;
    }
    @PostMapping
    public ResponseEntity<?>postexpense(@RequestBody ExpenseModel emp)
    {
        Expense ec=expo.postexpense(emp);
        if(ec!=null)
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(ec);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping
    public ResponseEntity<?> getAllExpenses()
    {
        return ResponseEntity.ok(expo.getAllExpenses());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id)
    {
        try
        {
            return ResponseEntity.ok(expo.getById(id));
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
    public ResponseEntity<?> UpdateExpense(@PathVariable Long id,@RequestBody ExpenseModel emp)
    {
        try{
        return ResponseEntity.ok(expo.UpdateExpense(id,emp));
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
            expo.DeleteById(id);
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
