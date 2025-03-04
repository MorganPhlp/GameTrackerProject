package com.et4.gametrackerproject.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class WinStreakDto {
    private UserDto user;

    private GameDto game;

    private Integer currentStreak = 0;

    private Integer bestStreak = 0;

    private Instant lastWin;
}
