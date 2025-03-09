package com.et4.gametrackerproject.dto;

import com.et4.gametrackerproject.enums.OnlineStatus;
import com.et4.gametrackerproject.enums.PrivacySetting;
import com.et4.gametrackerproject.enums.ProfilRank;
import com.et4.gametrackerproject.enums.ScreenTheme;
import com.et4.gametrackerproject.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class UserDto {
    private Integer id;

    private Instant creationDate;

    private Instant lastModifiedDate;

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

    @JsonIgnore
    private Set<FriendshipDto> friendShipsInitiated = new HashSet<>();

    @JsonIgnore
    private Set<FriendshipDto> friendshipsReceived = new HashSet<>();

    @JsonIgnore
    private Set<GameCommentDto> comments = new HashSet<>();

    @JsonIgnore
    private Set<GameCommentLikeDto> likes = new HashSet<>();

    @JsonIgnore
    private Set<GameLeaderboardDto> leaderboardsLines = new HashSet<>();

    private Set<GameProgressDto> progressions = new HashSet<>();

    @JsonIgnore
    private Set<GameRatingDto> ratings = new HashSet<>();

    private Set<GameRecommendationDto> recommendationsSent = new HashSet<>();

    private Set<GameRecommendationDto> recommendationsReceived = new HashSet<>();

    private Set<MessageDto> messagesSent = new HashSet<>();

    private Set<MessageDto> messagesReceived = new HashSet<>();

    private Set<NotificationDto> notifications = new HashSet<>();

    @JsonIgnore
    private Set<ReportDto> reportsSent = new HashSet<>();

    @JsonIgnore
    private Set<ReportDto> reportsAgainst = new HashSet<>();

    @JsonIgnore
    private Set<ReportDto> reportsResolved = new HashSet<>();

    private Set<UserAchievementDto> achievementsEarned = new HashSet<>();

    private Set<UserSanctionDto> sanctionsReceived = new HashSet<>();

    @JsonIgnore
    private Set<UserSanctionDto> sanctionsDistributed = new HashSet<>();

    private Set<WinStreakDto> winStreaks = new HashSet<>();

    public static UserDto fromEntity(User user) {
        if (user == null) {
            return null;
            //TODO: throw exception
        }

        return UserDto.builder()
                .id(user.getId())
                .creationDate(user.getCreationDate())
                .lastModifiedDate(user.getLastModifiedDate())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .birthDate(user.getBirthDate())
                .avatar(AvatarDto.fromEntity(user.getAvatar()))
                .country(user.getCountry())
                .isActive(user.getIsActive())
                .isAdmin(user.getIsAdmin())
                .privacySetting(user.getPrivacySetting())
                .totalGamesPlayed(user.getTotalGamesPlayed())
                .totalPlayTime(user.getTotalPlayTime())
                .points(user.getPoints())
                .userRank(user.getUserRank())
                .themePreference(user.getThemePreference())
                .lastLogin(user.getLastLogin())
                .onlineStatus(user.getOnlineStatus())
                .dailyGameSessions(user.getDailyGameSessions().stream().map(DailyGameSessionDto::fromEntity).collect(Collectors.toSet()))
                .favoriteGames(user.getFavoriteGames().stream().map(FavoriteGameDto::fromEntity).collect(Collectors.toSet()))
                .progressions(user.getProgressions().stream().map(GameProgressDto::fromEntity).collect(Collectors.toSet()))
                .recommendationsSent(user.getRecommendationsSent().stream().map(GameRecommendationDto::fromEntity).collect(Collectors.toSet()))
                .recommendationsReceived(user.getRecommendationsReceived().stream().map(GameRecommendationDto::fromEntity).collect(Collectors.toSet()))
                .messagesSent(user.getMessagesSent().stream().map(MessageDto::fromEntity).collect(Collectors.toSet()))
                .messagesReceived(user.getMessagesReceived().stream().map(MessageDto::fromEntity).collect(Collectors.toSet()))
                .notifications(user.getNotifications().stream().map(NotificationDto::fromEntity).collect(Collectors.toSet()))
                .achievementsEarned(user.getAchievementsEarned().stream().map(UserAchievementDto::fromEntity).collect(Collectors.toSet()))
                .sanctionsReceived(user.getSanctionsReceived().stream().map(UserSanctionDto::fromEntity).collect(Collectors.toSet()))
                .winStreaks(user.getWinStreaks().stream().map(WinStreakDto::fromEntity).collect(Collectors.toSet()))
                .build();
    }

    public static User toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
            //TODO throw exception
        }

        return User.builder()
                .id(userDto.getId())
                .creationDate(userDto.getCreationDate())
                .lastModifiedDate(userDto.getLastModifiedDate())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .birthDate(userDto.getBirthDate())
                .avatar(AvatarDto.toEntity(userDto.getAvatar()))
                .country(userDto.getCountry())
                .isActive(userDto.getIsActive())
                .isAdmin(userDto.getIsAdmin())
                .privacySetting(userDto.getPrivacySetting())
                .totalGamesPlayed(userDto.getTotalGamesPlayed())
                .totalPlayTime(userDto.getTotalPlayTime())
                .points(userDto.getPoints())
                .userRank(userDto.getUserRank())
                .themePreference(userDto.getThemePreference())
                .lastLogin(userDto.getLastLogin())
                .onlineStatus(userDto.getOnlineStatus())
                .dailyGameSessions(userDto.getDailyGameSessions().stream().map(DailyGameSessionDto::toEntity).collect(Collectors.toSet()))
                .favoriteGames(userDto.getFavoriteGames().stream().map(FavoriteGameDto::toEntity).collect(Collectors.toSet()))
                .progressions(userDto.getProgressions().stream().map(GameProgressDto::toEntity).collect(Collectors.toSet()))
                .recommendationsSent(userDto.getRecommendationsSent().stream().map(GameRecommendationDto::toEntity).collect(Collectors.toSet()))
                .recommendationsReceived(userDto.getRecommendationsReceived().stream().map(GameRecommendationDto::toEntity).collect(Collectors.toSet()))
                .messagesSent(userDto.getMessagesSent().stream().map(MessageDto::toEntity).collect(Collectors.toSet()))
                .messagesReceived(userDto.getMessagesReceived().stream().map(MessageDto::toEntity).collect(Collectors.toSet()))
                .notifications(userDto.getNotifications().stream().map(NotificationDto::toEntity).collect(Collectors.toSet()))
                .achievementsEarned(userDto.getAchievementsEarned().stream().map(UserAchievementDto::toEntity).collect(Collectors.toSet()))
                .sanctionsReceived(userDto.getSanctionsReceived().stream().map(UserSanctionDto::toEntity).collect(Collectors.toSet()))
                .winStreaks(userDto.getWinStreaks().stream().map(WinStreakDto::toEntity).collect(Collectors.toSet()))
                .build();
    }
}
