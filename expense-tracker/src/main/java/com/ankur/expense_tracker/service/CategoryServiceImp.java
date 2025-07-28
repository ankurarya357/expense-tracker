package com.ankur.expense_tracker.service;

import com.ankur.expense_tracker.dto.CategoryDTO;
import com.ankur.expense_tracker.entity.Category;
import com.ankur.expense_tracker.exception.ResourceNotFoundException;
import com.ankur.expense_tracker.mapper.CategoryMapper;
import com.ankur.expense_tracker.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO getCategoryById(Long id){
     Category category = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category not found exception"));
     return CategoryMapper.toDTO(category);
    }

    public CategoryDTO getCategoryByName(String name){
        Category category = categoryRepository.findByName(name).orElseThrow(()->new ResourceNotFoundException(name + " Category not found exception"));
        return CategoryMapper.toDTO(category);
    }

    public List<CategoryDTO> getAllCategories(){
        List<Category> categories =  categoryRepository.findAll();
        return categories.stream().map(category -> CategoryMapper.toDTO(category)).collect(Collectors.toList());
    }

    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id){
        CategoryDTO category = getCategoryById(id);
        categoryRepository.delete( CategoryMapper.toEntity(category));
    }

    public CategoryDTO updateCategory(Long id,Category categoryUpdated){
        Category category = CategoryMapper.toEntity(getCategoryById(id));
        category.setName(categoryUpdated.getName());
        category.setDescription(categoryUpdated.getDescription());
        category.setCreatedAt(categoryUpdated.getCreatedAt());
        categoryRepository.save(category);
        return CategoryMapper.toDTO(category);
    }


}
