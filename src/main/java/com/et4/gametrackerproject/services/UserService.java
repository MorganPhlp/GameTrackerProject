package com.et4.gametrackerproject.services;

import com.et4.gametrackerproject.dto.UserDto;
import com.et4.gametrackerproject.enums.OnlineStatus;
import com.et4.gametrackerproject.enums.PrivacySetting;
import com.et4.gametrackerproject.enums.ProfilRank;
import com.et4.gametrackerproject.enums.ScreenTheme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService {

    // Gestion du cycle de vie
    UserDto createUser(UserDto userDto);
    UserDto updateUser(Integer userId, UserDto userDto);
    void deactivateUser(Integer userId);
    void deleteUser(Integer userId);

    // Récupération
    UserDto getUserById(Integer userId);
    UserDto getUserByUsername(String username);
    UserDto getUserByEmail(String email);
    Page<UserDto> getAllUsers(Pageable pageable);

    // Authentification et sécurité
    UserDto registerUser(UserDto userDto);
    UserDto loginUser(String identifier, String password);
    void resetPassword(Integer userId, String newPassword);
    void requestPasswordReset(String email);

    // Préférences utilisateur
    UserDto updatePrivacySettings(Integer userId, PrivacySetting privacy);
    UserDto updateThemePreference(Integer userId, ScreenTheme theme);
    UserDto updateAvatar(Integer userId, Integer avatarId);

    // Gestion des relations
    Page<UserDto> searchUsers(String query, Pageable pageable);
    Page<UserDto> getFriendsList(Integer userId, Pageable pageable);
    Page<UserDto> getMutualFriends(Integer user1Id, Integer user2Id, Pageable pageable);
    Page<UserDto> getBlockedUsers(Integer userId, Pageable pageable);

    // Statistiques et progression
    UserDto updatePlayStats(Integer userId, Integer gameTime, Integer gamesPlayed);
    UserDto addPoints(Integer userId, Integer points);
    Map<String, Object> getUserDashboard(Integer userId);
    Page<UserDto> getLeaderboard(Pageable pageable);

    // Administration
    Page<UserDto> getUsersByStatus(boolean isActive, Pageable pageable);

    // Gestion des sessions
    UserDto updateOnlineStatus(Integer userId, OnlineStatus status);
    UserDto recordLogin(Integer userId);
    void expireOldSessions(int daysThreshold);

    // Validation
    boolean isUsernameAvailable(String username);
    boolean isEmailRegistered(String email);
    boolean isAdultUser(Integer userId);

    // Intégration sociale
    UserDto shareProfile(Integer userId, String platform);

    // Sécurité avancée
    void enableTwoFactorAuth(Integer userId);
    void disableTwoFactorAuth(Integer userId);
    void revokeAllSessions(Integer userId);

    // Gestion des données
    UserDto importUserData(Integer userId, String jsonData);
    String exportUserData(Integer userId);
    void anonymizeUserData(Integer userId);


    // Personnalisation avancée
    UserDto saveProfileLayout(Integer userId, String layoutConfig);
    UserDto updateSocialLinks(Integer userId, Map<String, String> links);
    UserDto setBiography(Integer userId, String bio);

    // Métadonnées
    Map<String, Object> getUserMetadata(Integer userId);
    Map<String, Long> getActivityHeatmap(Integer userId);

}