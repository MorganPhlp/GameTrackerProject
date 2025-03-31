package com.et4.gametrackerproject.controller.api;


import com.et4.gametrackerproject.dto.GameTagDto;
import com.et4.gametrackerproject.model.Game;
import com.et4.gametrackerproject.model.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

public interface GameTagApi {


    @PostMapping(value = APP_ROOT + "/tag/{associationId}/update")
    @Operation(summary = "Mettre à jour l'association d'une étiquette", description = "Mettre à jour l'association d'une étiquette")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Association mise à jour"),
            @ApiResponse(responseCode = "404", description = "Association non trouvée")
    })
    GameTagDto updateTagAssociation(@PathVariable Integer associationId,@RequestBody Integer newTagId);

    @PostMapping(value = APP_ROOT + "/game/{gameId}/tag/{tagId}")
    @Operation(summary = "Ajouter une étiquette à un jeu", description = "Ajouter une étiquette à un jeu")
    @ApiResponse(responseCode = "200", description = "Étiquette ajoutée au jeu")
    GameTagDto addTagToGame(@PathVariable Integer gameId, @PathVariable Integer tagId);

    @DeleteMapping(value = APP_ROOT + "/game/{gameId}/tag/{tagId}")
    @Operation(summary = "Supprimer une étiquette d'un jeu", description = "Supprimer une étiquette d'un jeu")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Étiquette supprimée du jeu"),
            @ApiResponse(responseCode = "404", description = "Étiquette ou jeu non trouvé")
    })
    void removeTagFromGame(@PathVariable Integer gameId, @PathVariable Integer tagId);

    @GetMapping(value = APP_ROOT + "/game/{gameId}/tags")
    @Operation(summary = "Récupérer les étiquettes d'un jeu", description = "Récupérer les étiquettes d'un jeu")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Étiquettes trouvées"),
            @ApiResponse(responseCode = "404", description = "Aucune étiquette trouvée pour ce jeu")
    })
    Page<GameTagDto> getTagsForGame(@PathVariable Integer gameId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/tag/{tagId}/games")
    @Operation(summary = "Récupérer les jeux associés à une étiquette", description = "Récupérer les jeux associés à une étiquette")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Jeux trouvés"),
            @ApiResponse(responseCode = "404", description = "Aucun jeu trouvé pour cette étiquette")
    })
    Page<GameTagDto> getGamesForTag(@PathVariable Integer tagId, Pageable pageable);

    @PostMapping(value = APP_ROOT + "/game/{gameId}/tags")
    @Operation(summary = "Ajouter plusieurs étiquettes à un jeu", description = "Ajouter plusieurs étiquettes à un jeu")
    @ApiResponse(responseCode = "200", description = "Étiquettes ajoutées au jeu")
    Set<GameTagDto> addMultipleTagsToGame(@PathVariable Integer gameId, Set<Tag> tags);

    @DeleteMapping(value = APP_ROOT + "/game/{gameId}/tags")
    @Operation(summary = "Supprimer plusieurs étiquettes d'un jeu", description = "Supprimer plusieurs étiquettes d'un jeu")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Étiquettes supprimées du jeu"),
            @ApiResponse(responseCode = "404", description = "Étiquettes ou jeu non trouvé")
    })
    int removeMultipleTagsFromGame(@PathVariable Integer gameId, Set<Tag> tags);
}
