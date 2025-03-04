package com.et4.gametrackerproject.dto;

import com.et4.gametrackerproject.enums.FriendshipStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FriendshipDto {
    private UserDto user1;

    private UserDto user2;

    private FriendshipStatus status = FriendshipStatus.PENDING;
}
