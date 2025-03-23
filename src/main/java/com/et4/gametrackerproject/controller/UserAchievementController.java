package com.et4.gametrackerproject.controller;

import com.et4.gametrackerproject.controller.api.UserAchievementApi;
import com.et4.gametrackerproject.dto.AchievementDto;
import com.et4.gametrackerproject.dto.UserAchievementDto;
import com.et4.gametrackerproject.services.UserAchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
public class UserAchievementController implements UserAchievementApi {

    @Autowired
    private UserAchievementService userAchievementService;

    public UserAchievementController(UserAchievementService userAchievementService) {
        this.userAchievementService = userAchievementService;
    }

    @Override
    public UserAchievementDto unlockAchievement(UserAchievementDto userAchievementDto) {
        return userAchievementService.unlockAchievement(userAchievementDto);
    }

    @Override
    public void deleteUserAchievement(Integer userAchievementId) {
        userAchievementService.deleteUserAchievement(userAchievementId);
    }

    @Override
    public UserAchievementDto getUserAchievementById(Integer userAchievementId) {
        return userAchievementService.getUserAchievementById(userAchievementId);
    }

    @Override
    public Page<UserAchievementDto> getAchievementsByUser(Integer userId, Pageable pageable) {
        return userAchievementService.getAchievementsByUser(userId, pageable);
    }

    @Override
    public Set<UserAchievementDto> getRecentUnlocks(Integer userId, int days) {
        return userAchievementService.getRecentUnlocks(userId, days);
    }

    @Override
    public boolean hasAchievement(Integer userId, Integer achievementId) {
        return userAchievementService.hasAchievement(userId, achievementId);
    }

    @Override
    public boolean hasAllPrerequisites(Integer userId, Integer achievementId) {
        return userAchievementService.hasAllPrerequisites(userId, achievementId);
    }

    @Override
    public Map<AchievementDto, Boolean> getAchievementProgress(Integer userId) {
        return userAchievementService.getAchievementProgress(userId);
    }

    @Override
    public Integer getTotalAchievementPoints(Integer userId) {
        return userAchievementService.getTotalAchievementPoints(userId);
    }

    @Override
    public Double getGlobalUnlockRate(Integer achievementId) {
        return userAchievementService.getGlobalUnlockRate(achievementId);
    }
}
