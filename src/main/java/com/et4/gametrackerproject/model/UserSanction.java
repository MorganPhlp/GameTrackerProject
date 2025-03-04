package com.et4.gametrackerproject.model;

import com.et4.gametrackerproject.enums.SanctionType;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "usersanction")
public class UserSanction extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private User admin;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private SanctionType type;

    @Column(name = "reason")
    private String reason;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;
}
