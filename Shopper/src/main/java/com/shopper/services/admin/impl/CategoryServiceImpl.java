package com.shopper.services.admin.impl;

import com.shopper.dto.CategoryDto;
import com.shopper.entites.Category;
import com.shopper.repository.CategoryRepository;
import com.shopper.services.admin.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        return this.categoryRepository.save(category).getDto();
    }

    public List<Category> getAllCategory() {
        return this.categoryRepository.getAllCategory();
    }
}
