package com.et4.gametrackerproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameTagDto {
    private GameDto game;

    private TagDto tag;
}
