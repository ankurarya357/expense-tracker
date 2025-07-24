package com.ankur.expense_tracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ankur.expense_tracker.model.Expense;

import java.util.List;


@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @GetMapping
    public List<Expense> getAllExpenses() {
        return List.of(
                new Expense(1L, "Lunch", 150.0),
                new Expense(2L, "Transport", 80.0)
        );
    }

}
