package com.et4.gametrackerproject.services;

import com.et4.gametrackerproject.dto.AchievementDto;
import com.et4.gametrackerproject.enums.AchievementRarity;
import com.et4.gametrackerproject.enums.AchievementType;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AchievementService {

    //Méthodes de récupération
    List<AchievementDto> getAllAchievements();
    AchievementDto getAchievementById(Integer id);
    List<AchievementDto> getAchievementsByType(AchievementType type);
    List<AchievementDto> getAchievementsByRarity(AchievementRarity rarity);
    List<AchievementDto> getActiveAchievements();
    List<AchievementDto> getVisibleAchievementsForUser(Integer userId);

    //Gestion des achievements
    AchievementDto createAchievement(AchievementDto achievementDto);
    AchievementDto updateAchievement(Integer id, AchievementDto achievementDto);


    //Progression utilisateur
    Set<AchievementDto> getUnlockedAchievementsForUser(Integer userId);
    Map<AchievementDto, Boolean> getUserAchievementProgress(Integer userId);
    boolean hasUserUnlockedAchievement(Integer userId, Integer achievementId);
    AchievementDto unlockAchievementForUser(Integer userId, Integer achievementId);
    int calculateUserAchievementPoints(Integer userId);

    //Évaluation des conditions
    void checkUserProgress(Integer userId, String contextType, Map<String, Object> contextData);

    //Achievement secrets
    List<AchievementDto> getSecretAchievements();
}