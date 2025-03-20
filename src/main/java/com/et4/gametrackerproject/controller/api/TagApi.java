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

    @GetMapping(value = APP_ROOT + "/tags/popular", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<TagDto> getTagsByPopularity(Pageable pageable);

    // Gestion des relations

    @GetMapping(value = APP_ROOT + "/tags/related/{tagId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<TagDto> getRelatedTags(@PathVariable Integer tagId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/tags/game/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Set<TagDto> getTagsForGame(@PathVariable Integer gameId);

    @GetMapping(value = APP_ROOT + "/tags/common", produces = MediaType.APPLICATION_JSON_VALUE)
    Set<TagDto> getCommonTagsForGames(@RequestBody List<Integer> gameIds);

    // Recherche

    @GetMapping(value = APP_ROOT + "/tags/search/{query}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<TagDto> searchTags(@PathVariable String query, Pageable pageable);

    //Modération

    @GetMapping(value = APP_ROOT + "/tags/duplicates", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<TagDto> findDuplicateTags(Pageable pageable);

    //Batch operations

    @PostMapping(value = APP_ROOT + "/tags/batch", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    void batchCreateTags(@RequestBody Set<TagDto> tags);

    @DeleteMapping(value = APP_ROOT + "/tags/batch/delete")
    int batchDeleteUnusedTags();
}
