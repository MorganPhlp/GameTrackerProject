package com.et4.gametrackerproject.services.impl;

import com.et4.gametrackerproject.dto.GameTagDto;
import com.et4.gametrackerproject.services.GameTagService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GameTagServiceImpl implements GameTagService {
    @Override
    public GameTagDto addTagToGame(Integer gameId, Integer tagId) {
        return null;
    }

    @Override
    public void removeTagFromGame(Integer gameId, Integer tagId) {

    }

    @Override
    public GameTagDto updateTagAssociation(Integer associationId, Integer newTagId) {
        return null;
    }

    @Override
    public Page<GameTagDto> getTagsForGame(Integer gameId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<GameTagDto> getGamesForTag(Integer tagId, Pageable pageable) {
        return null;
    }

    @Override
    public GameTagDto getAssociationById(Integer associationId) {
        return null;
    }

    @Override
    public boolean hasTag(Integer gameId, Integer tagId) {
        return false;
    }

    @Override
    public Set<GameTagDto> addMultipleTagsToGame(Integer gameId, Set<Integer> tagIds) {
        return Set.of();
    }

    @Override
    public int removeMultipleTagsFromGame(Integer gameId, Set<Integer> tagIds) {
        return 0;
    }

    @Override
    public void syncGameTags(Integer gameId, Set<Integer> newTagIds) {

    }

    @Override
    public void recalculateTagUsageStats() {

    }

    @Override
    public void exportTagRelationships(String outputPath) {

    }

    @Override
    public void importTagRelationships(String inputPath) {

    }
}
