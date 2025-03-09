package com.et4.gametrackerproject.dto;

import com.et4.gametrackerproject.model.FavoriteGame;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FavoriteGameDto {
    private Integer id;

    @JsonIgnore
    private UserDto user;

    private GameDto game;

    public static FavoriteGameDto fromEntity(FavoriteGame favoriteGame) {
        if(favoriteGame == null) {
            return null;
            // TODO: throw exception
        }

        return FavoriteGameDto.builder()
            .id(favoriteGame.getId())
            .game(GameDto.fromEntity(favoriteGame.getGame()))
            .build();
    }

    public static FavoriteGame toEntity(FavoriteGameDto favoriteGameDto) {
        return FavoriteGame.builder()
            .id(favoriteGameDto.id)
            .game(GameDto.toEntity(favoriteGameDto.game))
            .build();
    }
}
