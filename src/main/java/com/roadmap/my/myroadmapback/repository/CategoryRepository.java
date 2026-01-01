package com.roadmap.my.myroadmapback.repository;

import com.roadmap.my.myroadmapback.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findAllByUserId(Long userId);
}
