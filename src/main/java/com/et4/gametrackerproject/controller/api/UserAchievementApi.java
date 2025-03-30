package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.AchievementDto;
import com.et4.gametrackerproject.dto.UserAchievementDto;
import com.et4.gametrackerproject.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

public interface UserAchievementApi {

    //Opérations de base

    @PostMapping(value = APP_ROOT + "/userAchievements/unlock", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Débloquer un achievement", description = "Débloquer un achievement")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "achievement débloquée"),
            @ApiResponse(responseCode = "404", description = "achievement non trouvée")
    })
    UserAchievementDto unlockAchievement(@RequestBody UserAchievementDto userAchievementDto);

    @DeleteMapping(value = APP_ROOT + "/userAchievements/delete/{userAchievementId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Supprimer un achievement", description = "Supprimer un achievement")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "achievement supprimée"),
            @ApiResponse(responseCode = "404", description = "achievement non trouvée")
    })
    void deleteUserAchievement(@PathVariable Integer userAchievementId);

    // Récupération

    @GetMapping(value = APP_ROOT + "/userAchievements/{userAchievementId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer un achievement par son ID", description = "Récupérer un achievement par son ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "achievement trouvée"),
            @ApiResponse(responseCode = "404", description = "achievement non trouvée")
    })
    UserAchievementDto getUserAchievementById(@PathVariable Integer userAchievementId);

    @GetMapping(value = APP_ROOT + "/userAchievements/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les achievements d'un utilisateur", description = "Récupérer les achievements d'un utilisateur")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "achievements trouvées"),
            @ApiResponse(responseCode = "404", description = "Aucun achievement trouvé pour cet utilisateur")
    })
    Page<UserAchievementDto> getAchievementsByUser(@PathVariable Integer userId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/userAchievements/recent/{userId}/{days}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les achievements débloqués récemment", description = "Récupérer les achievements débloqués récemment")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "achievements trouvées"),
            @ApiResponse(responseCode = "404", description = "Aucun achievement trouvé pour cet utilisateur")
    })
    Set<UserAchievementDto> getRecentUnlocks(@PathVariable Integer userId,@PathVariable int days);

    @GetMapping(value = APP_ROOT + "/userAchievements/unlocked/{userId}/{achievementId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Vérifier si un achievement est débloqué", description = "Vérifier si un achievement est débloqué")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "achievement trouvée"),
            @ApiResponse(responseCode = "404", description = "achievement non trouvée")
    })
    boolean hasAchievement(@PathVariable Integer userId,@PathVariable Integer achievementId);

    @GetMapping(value = APP_ROOT + "/userAchievements/prerequisites/{userId}/{achievementId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Vérifier si un utilisateur a tous les prérequis pour débloquer un achievement", description = "Vérifier si un utilisateur a tous les prérequis pour débloquer un achievement")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "prérequis vérifiés"),
            @ApiResponse(responseCode = "404", description = "achievement non trouvée")
    })
    boolean hasAllPrerequisites(@PathVariable Integer userId,@PathVariable Integer achievementId);

    @GetMapping(value = APP_ROOT + "/userAchievements/progress/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer la progression des achievements d'un utilisateur", description = "Récupérer la progression des achievements d'un utilisateur")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "progression trouvée"),
            @ApiResponse(responseCode = "404", description = "Aucun achievement trouvé pour cet utilisateur")
    })
    Map<AchievementDto, Boolean> getAchievementProgress(@PathVariable Integer userId);

    // Statistiques

    @GetMapping(value = APP_ROOT + "/userAchievements/points/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer le nombre total de points d'achievement d'un utilisateur", description = "Récupérer le nombre total de points d'achievement d'un utilisateur")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "points trouvés"),
            @ApiResponse(responseCode = "404", description = "Aucun achievement trouvé pour cet utilisateur")
    })
    Integer getTotalAchievementPoints(@PathVariable Integer userId);

    @GetMapping(value = APP_ROOT + "/userAchievements/unlockRate/{achievementId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer le taux de déblocage d'un achievement", description = "Récupérer le taux de déblocage d'un achievement")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "taux de déblocage trouvé"),
            @ApiResponse(responseCode = "404", description = "Aucun achievement trouvé")
    })
    Double getGlobalUnlockRate(@PathVariable Integer achievementId);
}
