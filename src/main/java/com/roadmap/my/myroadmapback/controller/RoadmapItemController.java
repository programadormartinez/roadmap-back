package com.roadmap.my.myroadmapback.controller;


import com.roadmap.my.myroadmapback.dto.RoadmapItemDTO;
import com.roadmap.my.myroadmapback.model.RoadmapItem;
import com.roadmap.my.myroadmapback.service.RoadmapItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roadmap/items")
public class RoadmapItemController {
    private RoadmapItemService roadmapItemService;

    RoadmapItemController(RoadmapItemService roadmapItemService) {
        this.roadmapItemService = roadmapItemService;
    }

    @PostMapping
    public RoadmapItem insertRoadmapItem(@RequestBody RoadmapItemDTO dto) {
        return roadmapItemService.createRoadmapItem(dto);
    }

    @PutMapping("/{id}")
    public RoadmapItem updateRoadmapItem(@PathVariable(value = "id") Long id, @RequestBody RoadmapItemDTO dto) {
        return roadmapItemService.updateRoadmapItem(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteRoadmapItem(@PathVariable(value = "id") Long id) {
        roadmapItemService.deleteRoadmapItem(id);
    }
}
