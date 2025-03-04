package com.et4.gametrackerproject.dto;

import com.et4.gametrackerproject.enums.ReportStatus;
import com.et4.gametrackerproject.enums.ReportType;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ReportDto {
    private UserDto reporter;

    private UserDto reported;

    private ReportType type;

    private Integer contentId;

    private String reason;

    private ReportStatus status;

    private UserDto resolver;

    private Instant resolvedAt;
}
