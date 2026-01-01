package com.roadmap.my.myroadmapback.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private String icon;
    private String color;
    private String userId;
}
