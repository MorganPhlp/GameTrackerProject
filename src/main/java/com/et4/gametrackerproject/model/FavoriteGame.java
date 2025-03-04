package com.et4.gametrackerproject.model;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "favoritegame",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "game_id"})
        }
)
public class FavoriteGame extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;
}
