package com.et4.gametrackerproject.dto;

import com.et4.gametrackerproject.enums.NotifType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationDto {
    private UserDto user;

    private NotifType type;

    private String content;

    private Boolean isRead = false;
}
