package com.ankur.expense_tracker.repository;


import com.ankur.expense_tracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    public List<Expense> findByTitle(String title);

    public List<Expense> findByAmount(Double amount);

    @Query("SELECT e FROM Expense e WHERE e.category.name = :categoryName")
    List<Expense> findByCategoryName(@Param("categoryName") String categoryName);
}
