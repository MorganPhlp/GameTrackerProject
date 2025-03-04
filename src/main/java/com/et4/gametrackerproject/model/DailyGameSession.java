package com.et4.gametrackerproject.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "dailygamesession")
public class DailyGameSession extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Column(name = "date", nullable = false)
    private Instant date;

    @Column(name = "total_time_played")
    private Integer totalTimePlayed = 0;

    @Column(name = "games_played")
    private Integer gamesPlayed = 0;

    @Column(name = "unique_games_played")
    private Integer uniqueGamesPlayed = 0;
}
