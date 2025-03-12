package com.et4.gametrackerproject.services;

import com.et4.gametrackerproject.dto.GameTagDto;
import com.et4.gametrackerproject.dto.TagDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GameTagService {

    //Opérations de base
    GameTagDto addTagToGame(Integer gameId, Integer tagId);
    void removeTagFromGame(Integer gameId, Integer tagId);
    GameTagDto updateTagAssociation(Integer associationId, Integer newTagId);

    //Récupération
    Page<GameTagDto> getTagsForGame(Integer gameId, Pageable pageable);
    Page<GameTagDto> getGamesForTag(Integer tagId, Pageable pageable);
    GameTagDto getAssociationById(Integer associationId);
    boolean hasTag(Integer gameId, Integer tagId);

    //Gestion batch
    Set<GameTagDto> addMultipleTagsToGame(Integer gameId, Set<Integer> tagIds);
    int removeMultipleTagsFromGame(Integer gameId, Set<Integer> tagIds);
    void syncGameTags(Integer gameId, Set<Integer> newTagIds);

    // Administration
    void recalculateTagUsageStats();
    void exportTagRelationships(String outputPath);
    void importTagRelationships(String inputPath);

}