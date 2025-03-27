package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.TagDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

public interface TagApi {

    // Opérations de base

    @PostMapping(value = APP_ROOT + "/tags/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    TagDto createTag(@RequestBody TagDto tagDto);

    @PutMapping(value = APP_ROOT + "/tags/update/{tagId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    TagDto updateTag(@PathVariable Integer tagId,@RequestBody TagDto tagDto);

    @DeleteMapping(value = APP_ROOT + "/tags/delete/{tagId}")
    void deleteTag(@PathVariable Integer tagId);

    // Récupération

    @GetMapping(value = APP_ROOT + "/tags/{tagId}", produces = MediaType.APPLICATION_JSON_VALUE)
    TagDto getTagById(@PathVariable Integer tagId);

    @GetMapping(value = APP_ROOT + "/tags/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    TagDto getTagByName(@PathVariable String name);

    @GetMapping(value = APP_ROOT + "/tags/all", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<TagDto> getAllTags(Pageable pageable);

    // Gestion des relations

    @GetMapping(value = APP_ROOT + "/tags/game/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<TagDto> getTagsForGame(@PathVariable Integer gameId);

    // Recherche

    @GetMapping(value = APP_ROOT + "/tags/search/{query}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<TagDto> searchTags(@PathVariable String query, Pageable pageable);
}
