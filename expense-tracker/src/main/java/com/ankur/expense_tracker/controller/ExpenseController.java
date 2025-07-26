package com.ankur.expense_tracker.controller;

import com.ankur.expense_tracker.dto.ExpenseDTO;
import com.ankur.expense_tracker.mapper.ExpenseMapper;
import com.ankur.expense_tracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ankur.expense_tracker.entity.Expense;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/title/{title}")
    public ResponseEntity<List<ExpenseDTO>> getExpenseByTitle(@PathVariable String title){
        return new ResponseEntity<>(expenseService.getExpensesByTitleName(title),HttpStatus.OK);
    }

    @GetMapping("/amount/{amount}")
    public ResponseEntity<List<ExpenseDTO>> getExpenseByTitle(@PathVariable Double amount){
        return new ResponseEntity<>(expenseService.getExpensesByAmount(amount),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses() {
        return new ResponseEntity<>(expenseService.getAllExpenses(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable Long id) {
        Expense expense = expenseService.getExpenseById(id);
        return new ResponseEntity<>(ExpenseMapper.toDtO(expenseService.getExpenseById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ExpenseDTO> addExpense(@RequestBody ExpenseDTO expenseDTO){
        Expense savedExpense = expenseService.saveExpense(ExpenseMapper.toEntity(expenseDTO));
        return new ResponseEntity<>(ExpenseMapper.toDtO(savedExpense), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id){
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDTO> updateExpense(@PathVariable Long id,@RequestBody ExpenseDTO expenseDTO){
        Expense expense = expenseService.updateExpense(id,ExpenseMapper.toEntity(expenseDTO));
        return new ResponseEntity<>(ExpenseMapper.toDtO(expense),HttpStatus.OK);
    }


}
