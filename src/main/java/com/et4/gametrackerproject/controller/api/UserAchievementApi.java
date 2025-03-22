package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.AchievementDto;
import com.et4.gametrackerproject.dto.UserAchievementDto;
import com.et4.gametrackerproject.dto.UserDto;
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
    UserAchievementDto unlockAchievement(@RequestBody UserAchievementDto userAchievementDto);

    @DeleteMapping(value = APP_ROOT + "/userAchievements/delete/{userAchievementId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteUserAchievement(@PathVariable Integer userAchievementId);

    // Récupération

    @GetMapping(value = APP_ROOT + "/userAchievements/{userAchievementId}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserAchievementDto getUserAchievementById(@PathVariable Integer userAchievementId);

    @GetMapping(value = APP_ROOT + "/userAchievements/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<UserAchievementDto> getAchievementsByUser(@PathVariable Integer userId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/userAchievements/type/{achievementType}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<UserAchievementDto> getAchievementsByType(@PathVariable String achievementType, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/userAchievements/recent/{userId}/{days}", produces = MediaType.APPLICATION_JSON_VALUE)
    Set<UserAchievementDto> getRecentUnlocks(@PathVariable Integer userId,@PathVariable int days);

    // Vérifications

    @GetMapping(value = APP_ROOT + "/userAchievements/unlocked/{userId}/{achievementId}", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean hasAchievement(@PathVariable Integer userId,@PathVariable Integer achievementId);

    @GetMapping(value = APP_ROOT + "/userAchievements/prerequisites/{userId}/{achievementId}", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean hasAllPrerequisites(@PathVariable Integer userId,@PathVariable Integer achievementId);

    @GetMapping(value = APP_ROOT + "/userAchievements/progress/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<AchievementDto, Boolean> getAchievementProgress(@PathVariable Integer userId);

    // Gestion des dépendances

    @PostMapping(value = APP_ROOT + "/userAchievements/unlockDependent/{userId}/{baseAchievementId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void unlockDependentAchievements(@PathVariable Integer userId,@PathVariable Integer baseAchievementId);

    @PostMapping(value = APP_ROOT + "/userAchievements/checkAndUnlockChain/{userId}/{achievementChainId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void checkAndUnlockChainAchievements(@PathVariable Integer userId,@PathVariable Integer achievementChainId);

    @PostMapping(value = APP_ROOT + "/userAchievements/validateDependencies/{userAchievementId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void validateAchievementDependencies(@PathVariable Integer userAchievementId);

    // Statistiques

    @GetMapping(value = APP_ROOT + "/userAchievements/points/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Integer getTotalAchievementPoints(@PathVariable Integer userId);

    @GetMapping(value = APP_ROOT + "/userAchievements/distribution/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Long> getAchievementDistribution(@PathVariable Integer userId);

    @GetMapping(value = APP_ROOT + "/userAchievements/topCollectors/{limit}", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<UserDto, Integer> getTopAchievementCollectors(@PathVariable int limit);

    @GetMapping(value = APP_ROOT + "/userAchievements/unlockRate/{achievementId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Double getGlobalUnlockRate(@PathVariable Integer achievementId);

    // Administration

    @PostMapping(value = APP_ROOT + "/userAchievements/grant/{userId}/{achievementId}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserAchievementDto grantAchievement(@PathVariable Integer userId,@PathVariable Integer achievementId);

    @PostMapping(value = APP_ROOT + "/userAchievements/bulkGrant/{achievementId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void bulkGrantAchievement(@PathVariable Integer achievementId,@RequestBody List<Integer> userIds);

    // Personnalisation

    @GetMapping(value = APP_ROOT + "/userAchievements/rarest", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<UserAchievementDto> getRarestAchievements(Pageable pageable);

    @GetMapping(value = APP_ROOT + "/userAchievements/secrets/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Set<UserAchievementDto> getSecretAchievements(@PathVariable Integer userId);

    @GetMapping(value = APP_ROOT + "/userAchievements/unlockTimes/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<AchievementDto, Instant> getAchievementUnlockTimes(@PathVariable Integer userId);

    // Social

    @GetMapping(value = APP_ROOT + "/userAchievements/friend/{userId}/{friendId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<UserAchievementDto> getFriendAchievements(@PathVariable Integer userId,@PathVariable Integer friendId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/userAchievements/leaderboard/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<UserDto, Integer> getSocialLeaderboard(@PathVariable Integer userId);
}
