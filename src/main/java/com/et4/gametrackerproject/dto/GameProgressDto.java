package com.et4.gametrackerproject.dto;

import com.et4.gametrackerproject.enums.GameStatus;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class GameProgressDto {
    private UserDto user;

    private GameDto game;

    private GameStatus status = GameStatus.IN_PROGRESS;

    private Integer score;

    private Instant lastPlayed;

    private String progressData; // NE PAS CONVERTIR car ça va être stocké et réutilisé comme tel

    private Integer timePlayed = 0;

    private Integer attempts = 0;

    private Integer wins = 0;

    private Integer losses = 0;

    private Integer bestScore;

    private Integer currentStreak = 0;
}
