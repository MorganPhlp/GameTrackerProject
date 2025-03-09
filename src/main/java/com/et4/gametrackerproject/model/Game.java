package com.et4.gametrackerproject.model;

import com.et4.gametrackerproject.enums.DifficultyLevel;
import com.et4.gametrackerproject.enums.GameCategory;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "game")
public class Game extends AbstractEntity{

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "url", nullable = false, unique = true)
    private String url;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private GameCategory category;

    @Column(name = "average_rating")
    private Double averageRating;

    @Column(name = "play_count")
    private Integer playCount = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty_level")
    private DifficultyLevel difficultyLevel = DifficultyLevel.MEDIUM;

    @Column(name = "min_age")
    private Integer minAge;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private Set<FavoriteGame> favoriteGames = new HashSet<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private Set<GameComment> comments = new HashSet<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private Set<GameLeaderboard> leaderboardEntries = new HashSet<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private Set<GameProgress> progressions = new HashSet<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private Set<GameRating> ratings = new HashSet<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private Set<GameRecommendation> recommendations = new HashSet<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private Set<GameTag> tags = new HashSet<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private Set<WinStreak> winStreaks = new HashSet<>();
}
