package com.product_service.service;

import com.product_service.dto.CategoryDto;
import com.product_service.entity.Category;
import com.product_service.mapper.CategoryMapper;
import com.product_service.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categories = categoryRepository.findAll();

        List<CategoryDto> dtoList = new ArrayList<>();

        for (Category c : categories) {

            CategoryDto categoryDto = CategoryMapper.convertCategoryDto(c);

            dtoList.add(categoryDto);
        }
        return dtoList;
    }

    @Override
    public CategoryDto findByIdCategory(long id) {
        return null;
    }

    @Override
    public CategoryDto findByCategoryName(String name) {
        return null;
    }
}
