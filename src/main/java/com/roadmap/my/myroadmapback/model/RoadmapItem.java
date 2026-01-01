package com.roadmap.my.myroadmapback.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "roadmap_item")
public class RoadmapItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String status;
    private Integer progress;

    @Embedded
    private RoadmapNotes notes;

    @ElementCollection
    private List<String> links;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(cascade = CascadeType.ALL)
    private List<RoadmapItem> subItems;

}
