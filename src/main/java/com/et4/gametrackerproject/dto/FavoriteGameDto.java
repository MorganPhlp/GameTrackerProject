package com.et4.gametrackerproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FavoriteGameDto {
    private UserDto user;

    private GameDto game;
}
