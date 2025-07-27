package com.ankur.expense_tracker.controller;

import com.ankur.expense_tracker.dto.ExpenseDTO;
import com.ankur.expense_tracker.mapper.ExpenseMapper;
import com.ankur.expense_tracker.response.BaseResponse;
import com.ankur.expense_tracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ankur.expense_tracker.entity.Expense;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/title/{title}")
    public ResponseEntity<BaseResponse<List<ExpenseDTO>>> getExpenseByTitle(@PathVariable String title){
        return new ResponseEntity<>(new BaseResponse<>(true,"data found by title",expenseService.getExpensesByTitleName(title)),HttpStatus.OK);
    }

    @GetMapping("/amount/{amount}")
    public ResponseEntity<List<ExpenseDTO>> getExpenseByTitle(@PathVariable Double amount){
        return new ResponseEntity<>(expenseService.getExpensesByAmount(amount),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<ExpenseDTO>>> getAllExpenses() {
        return new ResponseEntity<>(new BaseResponse<>(true,"All expenses fetched",expenseService.getAllExpenses()),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ExpenseDTO>> getExpenseById(@PathVariable Long id) {
        Expense expense = expenseService.getExpenseById(id);
        BaseResponse<ExpenseDTO> response = new BaseResponse<>(true, "Expenses fetched successfully", ExpenseMapper.toDtO(expense));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<BaseResponse<ExpenseDTO>> addExpense(@RequestBody ExpenseDTO expenseDTO){
        Expense savedExpense = expenseService.saveExpense(ExpenseMapper.toEntity(expenseDTO));
        return new ResponseEntity<>(new BaseResponse<>(true,"Data Saved Successfully",ExpenseMapper.toDtO(savedExpense)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<ExpenseDTO>> deleteExpense(@PathVariable Long id){
        Expense expense = expenseService.getExpenseById(id);
        expenseService.deleteExpense(id);
        return new ResponseEntity<>(new BaseResponse<>(true,"Data deletion done",ExpenseMapper.toDtO(expense)),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<ExpenseDTO>> updateExpense(@PathVariable Long id,@RequestBody ExpenseDTO expenseDTO){
        Expense expense = expenseService.updateExpense(id,ExpenseMapper.toEntity(expenseDTO));
        return new ResponseEntity<>( new BaseResponse<>(true,"Data updated",ExpenseMapper.toDtO(expense)),HttpStatus.OK);
    }


}
