package com.et4.gametrackerproject.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(
        name = "gamerating",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "game_id"})
        }
)
public class GameRating extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @Column(name = "rating", nullable = false)
    private Integer rating;
}
