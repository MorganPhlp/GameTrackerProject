package com.et4.gametrackerproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameRecommendationDto {
    private UserDto sender;

    private UserDto receiver;

    private GameDto game;

    private String message;
}
