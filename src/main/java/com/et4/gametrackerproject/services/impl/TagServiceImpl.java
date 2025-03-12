package com.et4.gametrackerproject.services.impl;

import com.et4.gametrackerproject.dto.TagDto;
import com.et4.gametrackerproject.services.TagService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService {
    @Override
    public TagDto createTag(TagDto tagDto) {
        return null;
    }

    @Override
    public TagDto updateTag(Integer tagId, TagDto tagDto) {
        return null;
    }

    @Override
    public void deleteTag(Integer tagId) {

    }

    @Override
    public TagDto getTagById(Integer tagId) {
        return null;
    }

    @Override
    public TagDto getTagByName(String name) {
        return null;
    }

    @Override
    public Page<TagDto> getAllTags(Pageable pageable) {
        return null;
    }

    @Override
    public Page<TagDto> getTagsByPopularity(Pageable pageable) {
        return null;
    }

    @Override
    public Page<TagDto> getRelatedTags(Integer tagId, Pageable pageable) {
        return null;
    }

    @Override
    public Set<TagDto> getTagsForGame(Integer gameId) {
        return Set.of();
    }

    @Override
    public Set<TagDto> getCommonTagsForGames(List<Integer> gameIds) {
        return Set.of();
    }

    @Override
    public Page<TagDto> searchTags(String query, Pageable pageable) {
        return null;
    }

    @Override
    public Page<TagDto> findDuplicateTags(Pageable pageable) {
        return null;
    }

    @Override
    public void batchCreateTags(Set<TagDto> tags) {

    }

    @Override
    public int batchDeleteUnusedTags() {
        return 0;
    }
}
