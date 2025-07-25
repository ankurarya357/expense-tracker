package com.ankur.expense_tracker.model;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Expense {
    private Long id;
    private String description;
    private Double amountGiven;
    private Double amountTaken;
}