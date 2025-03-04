package com.et4.gametrackerproject.dto;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class GameCommentDto {
    private UserDto user;

    private GameDto game;

    private String content;

    private GameCommentDto parentComment;

    private Set<GameCommentDto> replies = new HashSet<>();

    private Set<GameCommentLikeDto> likes = new HashSet<>();
}
