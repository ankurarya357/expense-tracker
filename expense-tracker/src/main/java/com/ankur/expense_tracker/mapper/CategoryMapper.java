package com.ankur.expense_tracker.mapper;

import com.ankur.expense_tracker.dto.CategoryDTO;
import com.ankur.expense_tracker.entity.Category;

public class CategoryMapper {

    public static CategoryDTO toDTO(Category category){
        return new CategoryDTO(category.getId(),category.getName(),category.getDescription(),category.getCreatedAt());
    }

    public static Category toEntity(CategoryDTO dto){
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setCreatedAt(dto.getCreatedAt());
        return category;
    }
}
