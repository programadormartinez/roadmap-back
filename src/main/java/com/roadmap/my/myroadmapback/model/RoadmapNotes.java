package com.roadmap.my.myroadmapback.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable

@Data
public class RoadmapNotes {
    private String learned;
    private String application;
    private String additions;

}
