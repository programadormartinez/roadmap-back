package com.roadmap.my.myroadmapback.service.impl;

import com.roadmap.my.myroadmapback.dto.RoadmapItemDTO;
import com.roadmap.my.myroadmapback.model.Category;
import com.roadmap.my.myroadmapback.model.RoadmapItem;
import com.roadmap.my.myroadmapback.model.RoadmapNotes;
import com.roadmap.my.myroadmapback.model.User;
import com.roadmap.my.myroadmapback.repository.CategoryRepository;
import com.roadmap.my.myroadmapback.repository.RoadmapItemRepository;
import com.roadmap.my.myroadmapback.service.RoadmapItemService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RoadmapItemImpl implements RoadmapItemService {

    private final RoadmapItemRepository roadmapItemRepository;
    private final CategoryRepository categoryRepository;

    public RoadmapItemImpl(RoadmapItemRepository roadmapItemRepository, CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.roadmapItemRepository = roadmapItemRepository;
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public RoadmapItem updateRoadmapItem(Long roadmapId, RoadmapItemDTO roadmapItem) {
        User user = getCurrentUser();
        RoadmapItem existingItem = roadmapItemRepository.findById(roadmapId).orElse(null);

        if (existingItem != null) {
            if (existingItem.getCategory() != null && !existingItem.getCategory().getUser().getId().equals(user.getId())) {
                return null; // Or throw exception
            }

            existingItem.setTitle(roadmapItem.getTitle());
            existingItem.setDescription(roadmapItem.getDescription());

            if (roadmapItem.getCategoryId() != null) {
                try {
                    Long categoryId = Long.parseLong(roadmapItem.getCategoryId());
                    Category findCategory = categoryRepository.findById(categoryId).orElse(null);
                    if (findCategory != null) {
                        if (!findCategory.getUser().getId().equals(user.getId())) {
                             return null; // Or throw exception
                        }
                        existingItem.setCategory(findCategory);
                    }
                } catch (NumberFormatException e) {
                    // Ignore invalid category ID format
                }
            }

            existingItem.setStatus(roadmapItem.getStatus());
            existingItem.setProgress(roadmapItem.getProgress());
            existingItem.setLinks(roadmapItem.getLinks());

            if (existingItem.getNotes() == null) {
                existingItem.setNotes(new RoadmapNotes());
            }
            existingItem.getNotes().setLearned(roadmapItem.getLearned());
            existingItem.getNotes().setApplication(roadmapItem.getApplication());
            existingItem.getNotes().setAdditions(roadmapItem.getAdditions());

            return roadmapItemRepository.save(existingItem);
        }
        return existingItem;
    }

    @Override
    public RoadmapItem createRoadmapItem(RoadmapItemDTO roadmapItem) {
        User user = getCurrentUser();
        RoadmapItem newItem = new RoadmapItem();
        newItem.setTitle(roadmapItem.getTitle());
        newItem.setDescription(roadmapItem.getDescription());

        if (roadmapItem.getCategoryId() != null) {
            try {
                Long categoryId = Long.parseLong(roadmapItem.getCategoryId());
                Category findCategory = categoryRepository.findById(categoryId).orElse(null);
                if (findCategory != null) {
                    if (!findCategory.getUser().getId().equals(user.getId())) {
                        return null; // Or throw exception
                    }
                    newItem.setCategory(findCategory);
                }
            } catch (NumberFormatException e) {
                // Ignore invalid category ID format
                return null;
            }
        }

        newItem.setStatus(roadmapItem.getStatus());
        newItem.setProgress(roadmapItem.getProgress());
        newItem.setLinks(roadmapItem.getLinks());

        RoadmapNotes notes = new RoadmapNotes();
        notes.setLearned(roadmapItem.getLearned());
        notes.setApplication(roadmapItem.getApplication());
        notes.setAdditions(roadmapItem.getAdditions());
        newItem.setNotes(notes);

        return roadmapItemRepository.save(newItem);
    }

    @Override
    public void deleteRoadmapItem(Long roadmapId) {
        User user = getCurrentUser();
        RoadmapItem item = roadmapItemRepository.findById(roadmapId).orElse(null);
        if (item != null && item.getCategory() != null && item.getCategory().getUser().getId().equals(user.getId())) {
            roadmapItemRepository.deleteById(roadmapId);
        }
    }
}
