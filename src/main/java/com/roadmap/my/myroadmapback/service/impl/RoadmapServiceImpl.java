package com.roadmap.my.myroadmapback.service.impl;

import com.roadmap.my.myroadmapback.dto.CategoryDTO;
import com.roadmap.my.myroadmapback.model.Category;
import com.roadmap.my.myroadmapback.model.User;
import com.roadmap.my.myroadmapback.repository.CategoryRepository;
import com.roadmap.my.myroadmapback.service.RoadmapService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoadmapServiceImpl implements RoadmapService {

    private final CategoryRepository categoryRepository;

    public RoadmapServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return categoryRepository.findAllByUserId(user.getId());
    }

    @Override
    public Category addCategory(CategoryDTO categoryDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setIcon(categoryDTO.getIcon());
        category.setColor(categoryDTO.getColor());
        category.setUser(user);
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Category findCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));

        if (!findCategory.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized access to category");
        }

        findCategory.setName(categoryDTO.getName());
        findCategory.setIcon(categoryDTO.getIcon());
        findCategory.setColor(categoryDTO.getColor());
        return categoryRepository.save(findCategory);
    }


    @Override
    public void deleteCategory(Long categoryId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Category findCategory = categoryRepository.findById(categoryId).orElse(null);
        if (findCategory != null && findCategory.getUser().getId().equals(user.getId())) {
            categoryRepository.deleteById(categoryId);
        }
    }
}
