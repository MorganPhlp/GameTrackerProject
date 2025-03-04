package com.et4.gametrackerproject.dto;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class AvatarDto {
    private String photo;

    private Set<UserDto> users = new HashSet<>();
}
