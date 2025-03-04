package com.et4.gametrackerproject.dto;

import com.et4.gametrackerproject.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageDto {
    private UserDto sender;

    private UserDto receiver;

    private String content;

    private Boolean isRead = false;
}
