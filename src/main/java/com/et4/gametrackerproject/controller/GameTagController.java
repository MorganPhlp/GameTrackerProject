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
    public GameTagDto addTagToGame(Game game, Tag tag) {
        return gameTagService.addTagToGame(game,tag);
    }

    @Override
    public void removeTagFromGame(Game game, Tag tag) {
        gameTagService.removeTagFromGame(game,tag);
    }

    @Override
    public GameTagDto updateTagAssociation(Integer associationId, Integer newTagId) {
        return gameTagService.updateTagAssociation(associationId,newTagId);
    }

    @Override
    public Page<GameTagDto> getTagsForGame(Game game, Pageable pageable) {
        return gameTagService.getTagsForGame(game,pageable);
    }

    @Override
    public Page<GameTagDto> getGamesForTag(Tag tag, Pageable pageable) {
        return gameTagService.getGamesForTag(tag,pageable);
    }

    @Override
    public Set<GameTagDto> addMultipleTagsToGame(Game game, Set<Tag> tags) {
        return gameTagService.addMultipleTagsToGame(game,tags);
    }

    @Override
    public int removeMultipleTagsFromGame(Game game, Set<Tag> tags) {
        return gameTagService.removeMultipleTagsFromGame(game,tags);
    }
}
