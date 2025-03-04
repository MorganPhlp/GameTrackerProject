package com.et4.gametrackerproject.model;

import com.et4.gametrackerproject.enums.ReportStatus;
import com.et4.gametrackerproject.enums.ReportType;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "report")
public class Report extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "reporter_id", nullable = false)
    private User reporter;

    @ManyToOne
    @JoinColumn(name = "reported_id", nullable = false)
    private User reported;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ReportType type;

    @Column(name = "content_id", nullable = false)
    private Integer contentId;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReportStatus status;

    @ManyToOne
    @JoinColumn(name = "resolved_by")
    private User resolver;

    @Column(name = "resolved_at")
    private Instant resolvedAt;
}
