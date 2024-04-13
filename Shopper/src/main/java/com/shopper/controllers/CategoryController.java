package com.shopper.controllers;

import com.shopper.dto.CategoryDto;
import com.shopper.entites.Category;
import com.shopper.services.admin.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/admin/")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("category")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto saveCategory = this.categoryService.createCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveCategory);
    }

    @GetMapping("category")
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(this.categoryService.getAllCategory());
    }
}
