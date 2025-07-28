package com.ankur.expense_tracker.service;

import com.ankur.expense_tracker.dto.ExpenseDTO;
import com.ankur.expense_tracker.entity.Category;
import com.ankur.expense_tracker.entity.Expense;
import com.ankur.expense_tracker.exception.ResourceNotFoundException;
import com.ankur.expense_tracker.exception.TitleNotFoundExcepton;
import com.ankur.expense_tracker.mapper.ExpenseMapper;
import com.ankur.expense_tracker.repository.CategoryRepository;
import com.ankur.expense_tracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ExpenseService(ExpenseRepository expenseRepository, CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
    }

    public Expense saveExpense(ExpenseDTO expenseDTO) {
        Category category = categoryRepository.findByName(expenseDTO.getCategoryName())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with Name: " + expenseDTO.getCategoryName()));
        Expense expense = ExpenseMapper.toEntity(expenseDTO,category);
        return expenseRepository.save(expense);
    }

    public List<ExpenseDTO> getAllExpenses() {
        List<Expense> expenses =  expenseRepository.findAll();
        return expenses.stream().map(expense -> ExpenseMapper.toDtO(expense)).collect(Collectors.toList());
    }

    public List<Expense> getExpensesByCategoryName(String categoryName) {
        return expenseRepository.findByCategoryName(categoryName);
    }

    public Expense getExpenseById(Long id) {
        return  expenseRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource Not found Exception"));
    }

    public List<ExpenseDTO> getExpensesByTitleName(String title) {
        List<Expense> expenses = expenseRepository.findByTitle(title);
        if (expenses.isEmpty()) {
            throw new TitleNotFoundExcepton("Title '" + title + "' not found in the system");
        }
        return expenses.stream().map(ExpenseMapper::toDtO).collect(Collectors.toList());
    }



    public List<ExpenseDTO> getExpensesByAmount(Double amount) {
        return expenseRepository.findByAmount(amount).stream().map(ExpenseMapper::toDtO).collect(Collectors.toList());
    }


    public void deleteExpense(Long id) {
        Expense expense = getExpenseById(id);
        expenseRepository.delete(expense);
    }

    public Expense updateExpense(Long id, Expense expenseDetails) {
        Expense expense = getExpenseById(id);
        expense.setTitle(expenseDetails.getTitle());
        expense.setAmount(expenseDetails.getAmount());
        expense.setDate(expenseDetails.getDate());
        return expenseRepository.save(expense);
    }
}
