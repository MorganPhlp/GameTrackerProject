package com.et4.gametrackerproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameCommentLikeDto {
    private UserDto user;

    private GameCommentDto comment;
}
