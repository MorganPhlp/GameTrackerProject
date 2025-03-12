package com.et4.gametrackerproject.services.impl;

import com.et4.gametrackerproject.dto.AchievementDto;
import com.et4.gametrackerproject.enums.AchievementRarity;
import com.et4.gametrackerproject.enums.AchievementType;
import com.et4.gametrackerproject.services.AchievementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class AchievementServiceImpl implements AchievementService {

    @Override
    public List<AchievementDto> getAllAchievements() {
        return List.of();
    }

    @Override
    public AchievementDto getAchievementById(Integer id) {
        return null;
    }

    @Override
    public List<AchievementDto> getAchievementsByType(AchievementType type) {
        return List.of();
    }

    @Override
    public List<AchievementDto> getAchievementsByRarity(AchievementRarity rarity) {
        return List.of();
    }

    @Override
    public List<AchievementDto> getActiveAchievements() {
        return List.of();
    }

    @Override
    public List<AchievementDto> getVisibleAchievementsForUser(Integer userId) {
        return List.of();
    }

    @Override
    public AchievementDto createAchievement(AchievementDto achievementDto) {
        return null;
    }

    @Override
    public AchievementDto updateAchievement(Integer id, AchievementDto achievementDto) {
        return null;
    }

    @Override
    public Set<AchievementDto> getUnlockedAchievementsForUser(Integer userId) {
        return Set.of();
    }

    @Override
    public Map<AchievementDto, Boolean> getUserAchievementProgress(Integer userId) {
        return Map.of();
    }

    @Override
    public boolean hasUserUnlockedAchievement(Integer userId, Integer achievementId) {
        return false;
    }

    @Override
    public AchievementDto unlockAchievementForUser(Integer userId, Integer achievementId) {
        return null;
    }

    @Override
    public int calculateUserAchievementPoints(Integer userId) {
        return 0;
    }

    @Override
    public void checkUserProgress(Integer userId, String contextType, Map<String, Object> contextData) {

    }

    @Override
    public void checkAchievements() {

    }

    @Override
    public void checkGameCompletionAchievements(Integer userId, Integer gameId, Map<String, Object> gameStats) {

    }

    @Override
    public Map<AchievementRarity, Long> getGlobalAchievementStats() {
        return Map.of();
    }

    @Override
    public List<AchievementDto> getSecretAchievements() {
        return List.of();
    }
}
