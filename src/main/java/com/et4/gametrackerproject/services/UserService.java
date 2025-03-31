package com.et4.gametrackerproject.services;

import com.et4.gametrackerproject.dto.ChangerMdpUserDto;
import com.et4.gametrackerproject.dto.UserDto;
import com.et4.gametrackerproject.enums.OnlineStatus;
import com.et4.gametrackerproject.enums.PrivacySetting;
import com.et4.gametrackerproject.enums.ProfilRank;
import com.et4.gametrackerproject.enums.ScreenTheme;
import com.et4.gametrackerproject.model.User;
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
    void deleteUser(Integer userId);

    // Récupération
    UserDto getUserById(Integer userId);
    UserDto getUserByUsername(String username);
    UserDto getUserByEmail(String email);
    Page<UserDto> getAllUsers(Pageable pageable);

    // Authentification et sécurité
    void resetPassword(Integer userId, String newPassword);
    void requestPasswordReset(String email);

    // Préférences utilisateur
    UserDto updatePrivacySettings(Integer userId, PrivacySetting privacy);
    UserDto updateThemePreference(Integer userId, ScreenTheme theme);
    UserDto updateAvatar(Integer userId, Integer avatarId);

    // Gestion des relations
    Page<UserDto> searchUsers(String query, Pageable pageable);
    Page<UserDto> getFriendsList(Integer userId, Pageable pageable);

    // Statistiques et progression
    UserDto updatePlayStats(Integer userId, Integer gameTime, Integer gamesPlayed);
    UserDto addPoints(Integer userId, Integer points);

    // Administration
    Page<UserDto> getUsersByStatus(boolean isActive, Pageable pageable);

    // Gestion des sessions
    UserDto updateOnlineStatus(Integer userId, OnlineStatus status);
    UserDto recordLogin(Integer userId);

    // Validation
    boolean isUsernameAvailable(String username);
    boolean isEmailRegistered(String email);
    boolean isAdultUser(Integer userId);

    // Intégration sociale
    UserDto shareProfile(Integer userId, String platform);

    // Gestion des données
    UserDto importUserData(Integer userId, String jsonData);
    String exportUserData(Integer userId);

    //Mot de de passe
    UserDto changerMdp(ChangerMdpUserDto dto);

}