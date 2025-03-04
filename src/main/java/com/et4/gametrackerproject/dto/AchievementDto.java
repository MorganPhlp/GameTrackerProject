package com.et4.gametrackerproject.dto;

import com.et4.gametrackerproject.enums.AchievementRarity;
import com.et4.gametrackerproject.enums.AchievementType;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@Builder
public class AchievementDto {
    private String name;

    private String description;

    private String icon;

    private Map<String, Object> conditions;

    private Boolean isActive = true;

    private AchievementType type;

    private Integer pointsReward = 0;

    private AchievementRarity rarity;

    private Integer requiresAchievementId;

    private Boolean isSecret = false;

    private Set<UserAchievementDto> achievementsEarned = new HashSet<>();
}
