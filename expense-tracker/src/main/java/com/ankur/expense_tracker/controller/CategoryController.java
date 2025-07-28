package com.ankur.expense_tracker.controller;

import com.ankur.expense_tracker.dto.CategoryDTO;
import com.ankur.expense_tracker.dto.ExpenseDTO;
import com.ankur.expense_tracker.entity.Category;
import com.ankur.expense_tracker.mapper.CategoryMapper;
import com.ankur.expense_tracker.response.BaseResponse;
import com.ankur.expense_tracker.service.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceImp categoryService;

    @GetMapping("/category/{id}")
    public ResponseEntity<BaseResponse<CategoryDTO>> getCategoryById(@PathVariable Long id){
        return new ResponseEntity<>(new BaseResponse<>(true, id + " Category Data Found",categoryService.getCategoryById(id)), HttpStatus.OK);
    }

    @PostMapping("/category")
    public ResponseEntity<BaseResponse<CategoryDTO>> saveCategory(@RequestBody Category category){
        return new ResponseEntity<>(new BaseResponse<>(true, "Category Created", CategoryMapper.toDTO(categoryService.saveCategory(category))), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<CategoryDTO>>> getAllCategories() {
        return new ResponseEntity<>(new BaseResponse<>(true,"All categories fetched",categoryService.getAllCategories()),HttpStatus.OK);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<BaseResponse<CategoryDTO>> deleteCategoryById(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(new BaseResponse<>(true, "Category Deleted", null),HttpStatus.OK);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<BaseResponse<CategoryDTO>> updateCategory(@PathVariable Long id,@RequestBody Category category){
        return new ResponseEntity<>(new BaseResponse<>(true, "Category Updated", categoryService.updateCategory(id,category)),HttpStatus.OK);
    }
}
