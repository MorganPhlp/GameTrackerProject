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

    @GetMapping(value = APP_ROOT + "/userAchievements/recent/{userId}/{days}", produces = MediaType.APPLICATION_JSON_VALUE)
    Set<UserAchievementDto> getRecentUnlocks(@PathVariable Integer userId,@PathVariable int days);

    // Vérifications

    @GetMapping(value = APP_ROOT + "/userAchievements/unlocked/{userId}/{achievementId}", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean hasAchievement(@PathVariable Integer userId,@PathVariable Integer achievementId);

    @GetMapping(value = APP_ROOT + "/userAchievements/prerequisites/{userId}/{achievementId}", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean hasAllPrerequisites(@PathVariable Integer userId,@PathVariable Integer achievementId);

    @GetMapping(value = APP_ROOT + "/userAchievements/progress/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<AchievementDto, Boolean> getAchievementProgress(@PathVariable Integer userId);

    // Statistiques

    @GetMapping(value = APP_ROOT + "/userAchievements/points/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Integer getTotalAchievementPoints(@PathVariable Integer userId);

    @GetMapping(value = APP_ROOT + "/userAchievements/unlockRate/{achievementId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Double getGlobalUnlockRate(@PathVariable Integer achievementId);
}
