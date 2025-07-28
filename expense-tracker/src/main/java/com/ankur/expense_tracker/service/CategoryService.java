package com.ankur.expense_tracker.service;

import com.ankur.expense_tracker.dto.CategoryDTO;
import com.ankur.expense_tracker.entity.Category;

import java.util.List;

public interface CategoryService {

    CategoryDTO getCategoryById(Long id);
    List<CategoryDTO> getAllCategories();
    Category saveCategory(Category category);
    void deleteCategory(Long id);
    CategoryDTO updateCategory(Long id,Category categoryUpdated);

}
