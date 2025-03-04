package com.et4.gametrackerproject.dto;

import com.et4.gametrackerproject.enums.DifficultyLevel;
import com.et4.gametrackerproject.enums.GameCategory;
import com.et4.gametrackerproject.model.*;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class GameDto {
    private String name;

    private String url;

    private String description;

    private GameCategory category;

    private Double averageRating;

    private Integer playCount = 0;

    private DifficultyLevel difficultyLevel = DifficultyLevel.MEDIUM;

    private Integer minAge;

    private Boolean isActive = true;

    private Set<GameTagDto> gameTags = new HashSet<>();

    private Set<FavoriteGameDto> favoriteGames = new HashSet<>();

    private Set<GameCommentDto> comments = new HashSet<>();

    private Set<GameLeaderboardDto> leaderboardEntries = new HashSet<>();

    private Set<GameProgressDto> progressions = new HashSet<>();

    private Set<GameRatingDto> ratings = new HashSet<>();

    private Set<GameRecommendationDto> recommendations = new HashSet<>();

    private Set<GameTagDto> tags = new HashSet<>();

    private Set<WinStreakDto> winStreaks = new HashSet<>();
}
