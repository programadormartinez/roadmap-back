package com.roadmap.my.myroadmapback.service;

import com.roadmap.my.myroadmapback.dto.CategoryDTO;
import com.roadmap.my.myroadmapback.model.Category;
import java.util.List;


public interface RoadmapService {
    List<Category> getAllCategories();

    Category addCategory(CategoryDTO categoryDTO);

    Category updateCategory(Long categoryId, CategoryDTO categoryDTO);


    void deleteCategory(Long categoryId);
}

