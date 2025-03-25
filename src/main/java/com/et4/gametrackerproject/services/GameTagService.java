package com.et4.gametrackerproject.services;

import com.et4.gametrackerproject.dto.GameTagDto;
import com.et4.gametrackerproject.dto.TagDto;
import com.et4.gametrackerproject.model.Game;
import com.et4.gametrackerproject.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GameTagService {

    Set<GameTagDto> addMultipleTagsToGame(Game game, Set<Tag> tags);

    int removeMultipleTagsFromGame(Game game, Set<Tag> tags);

    void removeTagFromGame(Game game, Tag tag);

    GameTagDto updateTagAssociation(Integer associationId, Integer newTagId);

    Page<GameTagDto> getTagsForGame(Game game, Pageable pageable);

    Page<GameTagDto> getGamesForTag(Tag tag, Pageable pageable);

    GameTagDto addTagToGame(Game game, Tag tag);


}