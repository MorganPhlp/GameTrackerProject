package com.et4.gametrackerproject.dto;

import com.et4.gametrackerproject.enums.OnlineStatus;
import com.et4.gametrackerproject.enums.PrivacySetting;
import com.et4.gametrackerproject.enums.ProfilRank;
import com.et4.gametrackerproject.enums.ScreenTheme;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class UserDto {
    private String username;

    private String email;

    private String password;

    private LocalDate birthDate;

    private AvatarDto avatar;

    private String country;

    private Boolean isActive = true;

    private Boolean isAdmin = false;

    private PrivacySetting privacySetting = PrivacySetting.PUBLIC;

    private Integer totalGamesPlayed = 0;

    private Integer totalPlayTime = 0;

    private Integer points = 0;

    private ProfilRank userRank;

    private ScreenTheme themePreference = ScreenTheme.LIGHT;

    private Instant lastLogin;

    private OnlineStatus onlineStatus = OnlineStatus.OFFLINE;

    private Set<DailyGameSessionDto> dailyGameSessions = new HashSet<>();

    private Set<FavoriteGameDto> favoriteGames = new HashSet<>();

    private Set<FriendshipDto> friendShipsInitiated = new HashSet<>();

    private Set<FriendshipDto> friendshipsReceived = new HashSet<>();

    private Set<GameCommentDto> comments = new HashSet<>();

    private Set<GameCommentLikeDto> likes = new HashSet<>();

    private Set<GameLeaderboardDto> leaderboardsLines = new HashSet<>();

    private Set<GameProgressDto> progressions = new HashSet<>();

    private Set<GameRatingDto> ratings = new HashSet<>();

    private Set<GameRecommendationDto> recommendationsSent = new HashSet<>();

    private Set<GameRecommendationDto> recommendationsReceived = new HashSet<>();

    private Set<MessageDto> messagesSent = new HashSet<>();

    private Set<MessageDto> messagesReceived = new HashSet<>();

    private Set<NotificationDto> notifications = new HashSet<>();

    private Set<ReportDto> reportsSent = new HashSet<>();

    private Set<ReportDto> reportsAgainst = new HashSet<>();

    private Set<ReportDto> reportsResolved = new HashSet<>();

    private Set<UserAchievementDto> achievementsEarned = new HashSet<>();

    private Set<UserSanctionDto> sanctionsReceived = new HashSet<>();

    private Set<UserSanctionDto> sanctionsDistributed = new HashSet<>();

    private Set<WinStreakDto> winStreaks = new HashSet<>();
}
