package com.roadmap.my.myroadmapback.dto;

import com.roadmap.my.myroadmapback.model.RoadmapItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RoadmapItemDTO {
    private String title;
    private String description;

    private String categoryId;
    private String status;
    private Integer progress;
    private List<String> links;

    private String learned;
    private String application;
    private String additions;
}
