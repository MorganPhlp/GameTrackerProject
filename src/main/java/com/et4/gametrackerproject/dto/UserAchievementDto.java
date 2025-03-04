package com.et4.gametrackerproject.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class UserAchievementDto {
    private UserDto user;

    private AchievementDto achievement;

    private Instant unlockedAt = Instant.now();
}
