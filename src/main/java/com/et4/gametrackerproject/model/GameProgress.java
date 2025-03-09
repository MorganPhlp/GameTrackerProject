package com.et4.gametrackerproject.model;

import com.et4.gametrackerproject.enums.GameStatus;
import com.et4.gametrackerproject.util.JsonConverter;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(
        name = "gameprogress",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"game_id", "user_id"})
        }
)
public class GameProgress extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private GameStatus status = GameStatus.IN_PROGRESS;

    @Column(name = "score")
    private Integer score;

    @Column(name = "last_played")
    private Instant lastPlayed;

    @Column(name = "progress_data", columnDefinition = "json")
    private String progressData; // NE PAS CONVERTIR car ça va être stocké et réutilisé comme tel

    @Column(name = "time_played")
    private Integer timePlayed = 0;

    @Column(name = "attempts")
    private Integer attempts = 0;

    @Column(name = "wins")
    private Integer wins = 0;

    @Column(name = "losses")
    private Integer losses = 0;

    @Column(name = "best_score")
    private Integer bestScore;

    @Column(name = "current_streak")
    private Integer currentStreak = 0;
}
