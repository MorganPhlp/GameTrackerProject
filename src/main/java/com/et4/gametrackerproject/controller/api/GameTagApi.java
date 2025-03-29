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


    @PostMapping(value = APP_ROOT + "/tag/{associationId}/update")
    GameTagDto updateTagAssociation(@PathVariable Integer associationId,@RequestBody Integer newTagId);

    @PostMapping(value = APP_ROOT + "/game/{game}/tag/{tag}")
    GameTagDto addTagToGame(@PathVariable Game game, @PathVariable Tag tag);

    @DeleteMapping(value = APP_ROOT + "/game/{game}/tag/{tag}")
    void removeTagFromGame(@PathVariable Game game, @PathVariable Tag tag);

    @GetMapping(value = APP_ROOT + "/game/{game}/tags")
    Page<GameTagDto> getTagsForGame(@PathVariable Game game, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/tag/{tag}/games")
    Page<GameTagDto> getGamesForTag(@PathVariable Tag tag, Pageable pageable);

    @PostMapping(value = APP_ROOT + "/game/{game}/tags")
    Set<GameTagDto> addMultipleTagsToGame(@PathVariable Game game, Set<Tag> tags);

    @DeleteMapping(value = APP_ROOT + "/game/{game}/tags")
    int removeMultipleTagsFromGame(@PathVariable Game game, Set<Tag> tags);
}
