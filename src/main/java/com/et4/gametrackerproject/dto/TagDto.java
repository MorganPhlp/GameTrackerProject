package com.et4.gametrackerproject.dto;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class TagDto {
    private String name;

    private Set<GameTagDto> gameTags = new HashSet<>();
}
