package com.ankur.expense_tracker.controller;

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

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        return new ResponseEntity<>(expenseService.getAllExpenses(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense){
       return new ResponseEntity<>(expenseService.saveExpense(expense), HttpStatus.CREATED);
    }


}
