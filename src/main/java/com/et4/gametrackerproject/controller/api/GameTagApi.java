package com.et4.gametrackerproject.controller.api;


import com.et4.gametrackerproject.dto.GameTagDto;
import com.et4.gametrackerproject.model.Game;
import com.et4.gametrackerproject.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

public interface GameTagApi {

    //Opérations de base

    @PostMapping(value = APP_ROOT + "/tag/{tagId}/game/{gameId}/add")
    GameTagDto addTagToGame(@PathVariable Integer gameId,@PathVariable Integer tagId);

    GameTagDto addTagToGame(Game game, Tag tag);

    @DeleteMapping(value = APP_ROOT + "/tag/{tagId}/game/{gameId}/remove")
    void removeTagFromGame(@PathVariable Integer gameId,@PathVariable Integer tagId);

    void removeTagFromGame(Game game, Tag tag);

    @PostMapping(value = APP_ROOT + "/tag/{associationId}/update")
    GameTagDto updateTagAssociation(@PathVariable Integer associationId,@RequestBody Integer newTagId);

    //Récupération

    @GetMapping(value = APP_ROOT + "/tag/game/{gameId}/tags", produces = "application/json")
    Page<GameTagDto> getTagsForGame(@PathVariable Integer gameId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/tag/tag/{tagId}/games", produces = "application/json")
    Page<GameTagDto> getGamesForTag(@PathVariable Integer tagId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/tag/{associationId}", produces = "application/json")
    GameTagDto getAssociationById(@PathVariable Integer associationId);

    @GetMapping(value = APP_ROOT + "/tag/{gameId}/tag/{tagId}/exists", produces = "application/json")
    boolean hasTag(@PathVariable Integer gameId,@PathVariable Integer tagId);

    //Gestion batch

    @PostMapping(value = APP_ROOT + "/tag/{gameId}/addMultiple")
    Set<GameTagDto> addMultipleTagsToGame(@PathVariable Integer gameId,@RequestBody Set<Integer> tagIds);

    @DeleteMapping(value = APP_ROOT + "/tag/{gameId}/removeMultiple")
    int removeMultipleTagsFromGame(@PathVariable Integer gameId,@RequestBody Set<Integer> tagIds);
}
