package com.roadmap.my.myroadmapback.controller;

import com.roadmap.my.myroadmapback.dto.CategoryDTO;
import com.roadmap.my.myroadmapback.model.Category;
import com.roadmap.my.myroadmapback.service.RoadmapService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roadmap/categories")
public class RoadmapController {

    private final RoadmapService roadmapService;

    public RoadmapController(RoadmapService roadmapService) {
        this.roadmapService = roadmapService;
    }

    @GetMapping
    public List<Category> getRoadmap() {
        return roadmapService.getAllCategories();
    }

    @PutMapping("/{id}")
    public Category updateRoadmap(@PathVariable(value = "id") Long id, @RequestBody CategoryDTO categoryDTO) {
        return roadmapService.updateCategory(id, categoryDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable(value = "id") Long id) {
        roadmapService.deleteCategory(id);
    }

    @PostMapping()
    public Category addCategory(@RequestBody CategoryDTO categoryDTO) {
        return roadmapService.addCategory(categoryDTO);
    }
}
