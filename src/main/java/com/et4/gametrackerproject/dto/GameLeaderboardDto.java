package com.et4.gametrackerproject.dto;

import com.et4.gametrackerproject.enums.LeaderboardPeriod;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class GameLeaderboardDto {
    private GameDto game;

    private UserDto user;

    private Integer score;

    private Integer rankNumber;

    private Instant date;

    private LeaderboardPeriod period;
}
