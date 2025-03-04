package com.et4.gametrackerproject.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class DailyGameSessionDto {
    UserDto user;

    private Instant date;

    private Integer totalTimePlayed = 0;

    private Integer gamesPlayed = 0;

    private Integer uniqueGamesPlayed = 0;
}
