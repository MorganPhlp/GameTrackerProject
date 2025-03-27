package com.et4.gametrackerproject.controller;

import com.et4.gametrackerproject.controller.api.GameTagApi;
import com.et4.gametrackerproject.dto.GameTagDto;
import com.et4.gametrackerproject.model.Game;
import com.et4.gametrackerproject.model.Tag;
import com.et4.gametrackerproject.services.GameTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class GameTagController implements GameTagApi {
    @Autowired
    private GameTagService gameTagService;

    public GameTagController(GameTagService gameTagService) {
        this.gameTagService = gameTagService;
    }


    @Override
    public GameTagDto addTagToGame(Integer gameId, Integer tagId) {
        return null;
    }

    @Override
    public GameTagDto addTagToGame(Game game, Tag tag) {
        return gameTagService.addTagToGame(game,tag);
    }

    @Override
    public void removeTagFromGame(Integer gameId, Integer tagId) {

    }

    @Override
    public void removeTagFromGame(Game game, Tag tag) {
        gameTagService.removeTagFromGame(game,tag);
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
}
