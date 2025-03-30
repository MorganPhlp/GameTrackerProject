package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.TagDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

public interface TagApi {

    @PostMapping(value = APP_ROOT + "/tags/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Créer un tag", description = "Créer un tag")
    @ApiResponse(responseCode = "200", description = "Tag créé")
    TagDto createTag(@RequestBody TagDto tagDto);

    @PutMapping(value = APP_ROOT + "/tags/update/{tagId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Mettre à jour un tag", description = "Mettre à jour un tag")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tag mis à jour"),
            @ApiResponse(responseCode = "404", description = "Tag non trouvé")
    })
    TagDto updateTag(@PathVariable Integer tagId,@RequestBody TagDto tagDto);

    @DeleteMapping(value = APP_ROOT + "/tags/delete/{tagId}")
    @Operation(summary = "Supprimer un tag", description = "Supprimer un tag")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tag supprimé"),
            @ApiResponse(responseCode = "404", description = "Tag non trouvé")
    })
    void deleteTag(@PathVariable Integer tagId);

    @GetMapping(value = APP_ROOT + "/tags/{tagId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer un tag par son ID", description = "Récupérer un tag par son ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tag trouvé"),
            @ApiResponse(responseCode = "404", description = "Tag non trouvé")
    })
    TagDto getTagById(@PathVariable Integer tagId);

    @GetMapping(value = APP_ROOT + "/tags/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer un tag par son nom", description = "Récupérer un tag par son nom")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tag trouvé"),
            @ApiResponse(responseCode = "404", description = "Tag non trouvé")
    })
    TagDto getTagByName(@PathVariable String name);

    @GetMapping(value = APP_ROOT + "/tags/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer tous les tags", description = "Récupérer tous les tags")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des tags trouvée"),
            @ApiResponse(responseCode = "404", description = "Aucun tag trouvé")
    })
    Page<TagDto> getAllTags(Pageable pageable);

    // Gestion des relations

    @GetMapping(value = APP_ROOT + "/tags/game/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les tags d'un jeu", description = "Récupérer les tags d'un jeu")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tags trouvés"),
            @ApiResponse(responseCode = "404", description = "Aucun tag trouvé pour ce jeu")
    })
    List<TagDto> getTagsForGame(@PathVariable Integer gameId);

    @GetMapping(value = APP_ROOT + "/tags/search/{query}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher des tags", description = "Rechercher des tags")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tags trouvés"),
            @ApiResponse(responseCode = "404", description = "Aucun tag trouvé")
    })
    Page<TagDto> searchTags(@PathVariable String query, Pageable pageable);
}
