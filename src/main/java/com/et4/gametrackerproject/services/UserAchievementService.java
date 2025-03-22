package com.et4.gametrackerproject.services;

import com.et4.gametrackerproject.dto.UserAchievementDto;
import com.et4.gametrackerproject.dto.AchievementDto;
import com.et4.gametrackerproject.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserAchievementService {

    //Opérations de base
    UserAchievementDto unlockAchievement(UserAchievementDto userAchievementDto);
    void deleteUserAchievement(Integer userAchievementId);

    // Récupération
    UserAchievementDto getUserAchievementById(Integer userAchievementId);
    Page<UserAchievementDto> getAchievementsByUser(Integer userId, Pageable pageable);
    Page<UserAchievementDto> getAchievementsByType(String achievementType, Pageable pageable);
    Set<UserAchievementDto> getRecentUnlocks(Integer userId, int days);

    // Vérifications
    boolean hasAchievement(Integer userId, Integer achievementId);
    boolean hasAllPrerequisites(Integer userId, Integer achievementId);
    Map<AchievementDto, Boolean> getAchievementProgress(Integer userId);

    // Gestion des dépendances
    void unlockDependentAchievements(Integer userId, Integer baseAchievementId);
    void checkAndUnlockChainAchievements(Integer userId, Integer achievementChainId);
    void validateAchievementDependencies(Integer userAchievementId);

    // Statistiques
    Integer getTotalAchievementPoints(Integer userId);
    Map<String, Long> getAchievementDistribution(Integer userId);
    Map<UserDto, Integer> getTopAchievementCollectors(int limit);
    Double getGlobalUnlockRate(Integer achievementId);

    // Administration
    UserAchievementDto grantAchievement(Integer userId, Integer achievementId);
    void bulkGrantAchievement(Integer achievementId, List<Integer> userIds);
    void recalculateAllUserPoints();

    // Personnalisation
    Page<UserAchievementDto> getRarestAchievements(Pageable pageable);
    Set<UserAchievementDto> getSecretAchievements(Integer userId);
    Map<AchievementDto, Instant> getAchievementUnlockTimes(Integer userId);

    // Synchronisation
    void syncAchievementsAcrossPlatforms(Integer userId);
    void migrateAchievementProgress(Integer sourceUserId, Integer targetUserId);

    // Social
    Page<UserAchievementDto> getFriendAchievements(Integer userId, Integer friendId, Pageable pageable);
    Map<UserDto, Integer> getSocialLeaderboard(Integer userId);
}