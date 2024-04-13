package com.shopper.services.admin;

import com.shopper.dto.CategoryDto;
import com.shopper.entites.Category;

import java.util.List;

public interface CategoryService {

    public CategoryDto createCategory(CategoryDto categoryDto);
    public List<Category> getAllCategory();
}
