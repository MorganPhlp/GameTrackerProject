package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.WinStreakDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

public interface WinStreakApi {

    // Opérations de base
    @PostMapping(value = APP_ROOT + "/streaks/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Créer ou mettre à jour une série", description = "Créer ou mettre à jour une série")
    @ApiResponse(responseCode = "200", description = "Série créée ou mise à jour")
    WinStreakDto createOrUpdateStreak(@RequestBody WinStreakDto streakDto);

    @GetMapping(value = APP_ROOT + "/streaks/{streakId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer une série par son ID", description = "Récupérer une série par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Série trouvée"),
            @ApiResponse(responseCode = "404", description = "Aucune série trouvée avec cet ID")
    })
    WinStreakDto getStreakById(@PathVariable Integer streakId);

    @DeleteMapping(value = APP_ROOT + "/streaks/delete/{streakId}")
    @Operation(summary = "Supprimer une série", description = "Supprimer une série")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Série supprimée"),
            @ApiResponse(responseCode = "404", description = "Aucune série trouvée avec cet ID")
    })
    void resetStreak(@PathVariable Integer streakId);

    @PutMapping(value = APP_ROOT + "/streaks/increment/{userId}/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Incrémenter la série", description = "Incrémenter la série")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Série incrémentée"),
            @ApiResponse(responseCode = "404", description = "Aucune série trouvée avec cet ID")
    })
    WinStreakDto incrementStreak(@PathVariable Integer userId,@PathVariable Integer gameId);

    @PutMapping(value = APP_ROOT + "/streaks/reset/{userId}/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Réinitialiser la série actuelle", description = "Réinitialiser la série actuelle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Série réinitialisée"),
            @ApiResponse(responseCode = "404", description = "Aucune série trouvée avec cet ID")
    })
    WinStreakDto resetCurrentStreak(@PathVariable Integer userId,@PathVariable Integer gameId);

    @PutMapping(value = APP_ROOT + "/streaks/update/{userId}/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Mettre à jour la meilleure série", description = "Mettre à jour la meilleure série")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Meilleure série mise à jour"),
            @ApiResponse(responseCode = "404", description = "Aucune série trouvée avec cet ID")
    })
    WinStreakDto updateBestStreak(@PathVariable Integer userId,@PathVariable Integer gameId);

    @GetMapping(value = APP_ROOT + "/streaks/current/{userId}/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer la série actuelle", description = "Récupérer la série actuelle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Série actuelle trouvée"),
            @ApiResponse(responseCode = "404", description = "Aucune série trouvée avec cet ID")
    })
    WinStreakDto getCurrentStreak(@PathVariable Integer userId,@PathVariable Integer gameId);

    @GetMapping(value = APP_ROOT + "/streaks/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les séries d'un utilisateur", description = "Récupérer les séries d'un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Séries trouvées"),
            @ApiResponse(responseCode = "404", description = "Aucune série trouvée pour cet utilisateur")
    })
    Page<WinStreakDto> getStreaksByUser(@PathVariable Integer userId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/streaks/game/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les séries d'un jeu", description = "Récupérer les séries d'un jeu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Séries trouvées"),
            @ApiResponse(responseCode = "404", description = "Aucune série trouvée pour ce jeu")
    })
    Page<WinStreakDto> getStreaksByGame(@PathVariable Integer gameId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/streaks/active", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les séries actives", description = "Récupérer les séries actives")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Séries actives trouvées"),
            @ApiResponse(responseCode = "404", description = "Aucune série active trouvée")
    })
    Page<WinStreakDto> getActiveStreaks(Pageable pageable);

    // Statistiques

    @GetMapping(value = APP_ROOT + "/streaks/global/best/{limit}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les meilleures séries globales", description = "Récupérer les meilleures séries globales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Meilleures séries globales trouvées"),
            @ApiResponse(responseCode = "404", description = "Aucune série globale trouvée")
    })
    Map<Integer, Integer> getGlobalBestStreaks(@PathVariable int limit);

    @GetMapping(value = APP_ROOT + "/streaks/game/best/{gameId}/{limit}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les meilleures séries d'un jeu", description = "Récupérer les meilleures séries d'un jeu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Meilleures séries d'un jeu trouvées"),
            @ApiResponse(responseCode = "404", description = "Aucune série trouvée pour ce jeu")
    })
    Map<Integer, Integer> getGameBestStreaks(@PathVariable Integer gameId,@PathVariable int limit);

    // Vérifications

    @GetMapping(value = APP_ROOT + "/streaks/active/{userId}/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Vérifier si une série est active", description = "Vérifier si une série est active")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Série active trouvée"),
            @ApiResponse(responseCode = "404", description = "Aucune série active trouvée")
    })
    boolean isActiveStreak(@PathVariable Integer userId,@PathVariable Integer gameId);

    // Batch operations

    @DeleteMapping(value = APP_ROOT + "/streaks/reset/all/{gameId}")
    @Operation(summary = "Réinitialiser toutes les séries d'un jeu", description = "Réinitialiser toutes les séries d'un jeu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Séries réinitialisées"),
            @ApiResponse(responseCode = "404", description = "Aucune série trouvée pour ce jeu")
    })
    void resetAllStreaksForGame(@PathVariable Integer gameId);
}
