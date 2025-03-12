package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.AchievementDto;
import com.et4.gametrackerproject.enums.AchievementRarity;
import com.et4.gametrackerproject.enums.AchievementType;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

public interface AchievementApi {

    //Méthodes de récupération

    @GetMapping(value =APP_ROOT + "/achievements/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AchievementDto> getAllAchievements();

    @GetMapping(value = APP_ROOT + "/achievements/{idAchievement}", produces = MediaType.APPLICATION_JSON_VALUE)
    AchievementDto getAchievementById(@PathVariable("idAchievement") Integer id);

    @GetMapping(value = APP_ROOT + "/achievements/type/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AchievementDto> getAchievementsByType(@PathVariable AchievementType type);

    @GetMapping(value = APP_ROOT + "/achievements/rarity/{rarity}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AchievementDto> getAchievementsByRarity(@PathVariable AchievementRarity rarity);

    @GetMapping(value = APP_ROOT + "/achievements/active", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AchievementDto> getActiveAchievements();

    @GetMapping(value = APP_ROOT + "/achievements/visible/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AchievementDto> getVisibleAchievementsForUser(@PathVariable Integer userId);

    //Gestion des achievements

    @PostMapping(value = APP_ROOT + "/achievements/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    AchievementDto createAchievement(@RequestBody AchievementDto achievementDto);

    @PutMapping(value = APP_ROOT + "/achievements/update/{idAchievement}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    AchievementDto updateAchievement(@PathVariable("idAchievement") Integer id,@RequestBody AchievementDto achievementDto);

    //Progression utilisateur

    @GetMapping(value = APP_ROOT + "/achievements/unlocked/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Set<AchievementDto> getUnlockedAchievementsForUser(@PathVariable Integer userId);

    @GetMapping(value = APP_ROOT + "/achievements/progress/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<AchievementDto, Boolean> getUserAchievementProgress(@PathVariable Integer userId);

    @GetMapping(value = APP_ROOT + "/achievements/unlocked/{userId}/{achievementId}", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean hasUserUnlockedAchievement(@PathVariable Integer userId,@PathVariable Integer achievementId);

    @PostMapping(value = APP_ROOT + "/achievements/unlock/{userId}/{achievementId}", produces = MediaType.APPLICATION_JSON_VALUE)
    AchievementDto unlockAchievementForUser(@PathVariable Integer userId,@PathVariable Integer achievementId);

    @GetMapping(value = APP_ROOT + "/achievements/points/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    int calculateUserAchievementPoints(@PathVariable Integer userId);

    //Évaluation des conditions
    void checkUserProgress(Integer userId, String contextType, Map<String, Object> contextData);
    void checkAchievements();

    //Méthodes de vérification
    void checkGameCompletionAchievements(Integer userId, Integer gameId, Map<String, Object> gameStats);

    //Statistiques
    Map<AchievementRarity, Long> getGlobalAchievementStats();

    //Achievement secrets

    @GetMapping(value = APP_ROOT + "/achievements/secrets", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AchievementDto> getSecretAchievements();
}
