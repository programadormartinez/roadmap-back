package com.roadmap.my.myroadmapback.repository;

import com.roadmap.my.myroadmapback.model.RoadmapItem;
import org.springframework.data.repository.CrudRepository;

public interface RoadmapItemRepository extends CrudRepository<RoadmapItem, Long> {
}
