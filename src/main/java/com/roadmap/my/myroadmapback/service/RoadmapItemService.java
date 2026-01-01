package com.roadmap.my.myroadmapback.service;

import com.roadmap.my.myroadmapback.dto.RoadmapItemDTO;
import com.roadmap.my.myroadmapback.model.RoadmapItem;

public interface RoadmapItemService {
    RoadmapItem updateRoadmapItem(Long roadmapId, RoadmapItemDTO roadmapItem);

    RoadmapItem createRoadmapItem(RoadmapItemDTO roadmapItem);

    void deleteRoadmapItem(Long roadmapId);
}
