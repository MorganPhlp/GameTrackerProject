package com.et4.gametrackerproject.dto;

import com.et4.gametrackerproject.enums.SanctionType;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class UserSanctionDto {
    private UserDto user;

    private UserDto admin;

    private SanctionType type;

    private String reason;

    private Instant startDate;

    private Instant endDate;
}
