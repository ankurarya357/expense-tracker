package com.ankur.expense_tracker.mapper;

import com.ankur.expense_tracker.dto.ExpenseDTO;
import com.ankur.expense_tracker.entity.Expense;

public class ExpenseMapper {
    
    public static ExpenseDTO toDtO(Expense expense){
        return new ExpenseDTO(expense.getId(), expense.getTitle(), expense.getAmount(),expense.getDate());
    }

    public static Expense toEntity(ExpenseDTO dto){
        Expense expense = new Expense();
        expense.setId(dto.getId());
        expense.setTitle(dto.getTitle());
        expense.setAmount(dto.getAmount());
        expense.setDate(dto.getDate());
        return expense;
    }

}
