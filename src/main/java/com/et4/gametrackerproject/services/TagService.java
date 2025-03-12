package com.et4.gametrackerproject.services;

import com.et4.gametrackerproject.dto.TagDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TagService {

    // Opérations de base
    TagDto createTag(TagDto tagDto);
    TagDto updateTag(Integer tagId, TagDto tagDto);
    void deleteTag(Integer tagId);

    // Récupération
    TagDto getTagById(Integer tagId);
    TagDto getTagByName(String name);
    Page<TagDto> getAllTags(Pageable pageable);
    Page<TagDto> getTagsByPopularity(Pageable pageable);

    // Gestion des relations
    Page<TagDto> getRelatedTags(Integer tagId, Pageable pageable);
    Set<TagDto> getTagsForGame(Integer gameId);
    Set<TagDto> getCommonTagsForGames(List<Integer> gameIds);

    // Recherche
    Page<TagDto> searchTags(String query, Pageable pageable);

    //Modération
    Page<TagDto> findDuplicateTags(Pageable pageable);

    //Batch operations
    void batchCreateTags(Set<TagDto> tags);
    int batchDeleteUnusedTags();
}