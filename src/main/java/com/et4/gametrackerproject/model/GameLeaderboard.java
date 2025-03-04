package com.et4.gametrackerproject.model;

import com.et4.gametrackerproject.enums.LeaderboardPeriod;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(
        name = "game_leaderboard",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"game_id", "user_id", "period"})
        }
)
public class GameLeaderboard extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false)
    private Integer rankNumber;

    @Column(nullable = false)
    private Instant date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LeaderboardPeriod period;
}
