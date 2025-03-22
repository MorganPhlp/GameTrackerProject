package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.UserDto;
import com.et4.gametrackerproject.enums.OnlineStatus;
import com.et4.gametrackerproject.enums.PrivacySetting;
import com.et4.gametrackerproject.enums.ScreenTheme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

public interface UserApi {

    // Gestion du cycle de vie

    @PostMapping(value = APP_ROOT + "/users/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    UserDto createUser(@RequestBody UserDto userDto);

    @PutMapping(value = APP_ROOT + "/users/update/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    UserDto updateUser(@PathVariable Integer userId,@RequestBody UserDto userDto);

    @PutMapping(value = APP_ROOT + "/users/deactivate/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deactivateUser(@PathVariable Integer userId);

    @DeleteMapping(value = APP_ROOT + "/users/delete/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteUser(@PathVariable Integer userId);

    // Récupération

    @GetMapping(value = APP_ROOT + "/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto getUserById(@PathVariable Integer userId);

    @GetMapping(value = APP_ROOT + "/users/username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto getUserByUsername(@PathVariable String username);

    @GetMapping(value = APP_ROOT + "/users/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto getUserByEmail(@PathVariable String email);

    @GetMapping(value = APP_ROOT + "/users/all", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<UserDto> getAllUsers(Pageable pageable);

    // Authentification et sécurité

    @PostMapping(value = APP_ROOT + "/users/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    UserDto registerUser(@RequestBody UserDto userDto);

    @PostMapping(value = APP_ROOT + "/users/login", produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto loginUser(@RequestBody String identifier,@RequestBody String password);

    @PutMapping(value = APP_ROOT + "/users/reset-password/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void resetPassword(@PathVariable Integer userId,@RequestBody String newPassword);

    @PostMapping(value = APP_ROOT + "/users/request-password-reset", produces = MediaType.APPLICATION_JSON_VALUE)
    void requestPasswordReset(@RequestBody String email);

    // Préférences utilisateur

    @PutMapping(value = APP_ROOT + "/users/update-privacy/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto updatePrivacySettings(@PathVariable Integer userId,@RequestBody PrivacySetting privacy);

    @PutMapping(value = APP_ROOT + "/users/update-theme/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto updateThemePreference(@PathVariable Integer userId,@RequestBody ScreenTheme theme);

    @PutMapping(value = APP_ROOT + "/users/update-avatar/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto updateAvatar(@PathVariable Integer userId,@RequestBody Integer avatarId);

    // Gestion des relations

    @GetMapping(value = APP_ROOT + "/users/search/{query}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<UserDto> searchUsers(@PathVariable String query, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/users/friends/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<UserDto> getFriendsList(@PathVariable Integer userId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/users/mutual-friends/{user1Id}/{user2Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<UserDto> getMutualFriends(@PathVariable Integer user1Id,@PathVariable Integer user2Id, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/users/blocked/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<UserDto> getBlockedUsers(@PathVariable Integer userId, Pageable pageable);

    // Statistiques et progression

    @PutMapping(value = APP_ROOT + "/users/update-stats/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto updatePlayStats(@PathVariable Integer userId,@RequestBody Integer gameTime,@RequestBody Integer gamesPlayed);

    @PutMapping(value = APP_ROOT + "/users/add-points/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto addPoints(@PathVariable Integer userId,@RequestBody Integer points);

    @GetMapping(value = APP_ROOT + "/users/dashboard/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> getUserDashboard(@PathVariable Integer userId);

    @GetMapping(value = APP_ROOT + "/users/leaderboard", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<UserDto> getLeaderboard(Pageable pageable);

    // Administration

    @GetMapping(value = APP_ROOT + "/users/status/{isActive}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<UserDto> getUsersByStatus(@PathVariable boolean isActive, Pageable pageable);

    // Gestion des sessions

    @PutMapping(value = APP_ROOT + "/users/update-online-status/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto updateOnlineStatus(@PathVariable Integer userId,@RequestBody OnlineStatus status);

    @PutMapping(value = APP_ROOT + "/users/record-login/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto recordLogin(@PathVariable Integer userId);

    @DeleteMapping(value = APP_ROOT + "/users/expire-sessions/{daysThreshold}", produces = MediaType.APPLICATION_JSON_VALUE)
    void expireOldSessions(@PathVariable int daysThreshold);

    // Validation

    @GetMapping(value = APP_ROOT + "/users/check-username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean isUsernameAvailable(@PathVariable String username);

    @GetMapping(value = APP_ROOT + "/users/check-email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean isEmailRegistered(@PathVariable String email);

    @GetMapping(value = APP_ROOT + "/users/check-adult/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean isAdultUser(@PathVariable Integer userId);

    // Intégration sociale

    @PostMapping(value = APP_ROOT + "/users/share/{userId}/{platform}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto shareProfile(@PathVariable Integer userId,@PathVariable String platform);

    // Gestion des données

    @PostMapping(value = APP_ROOT + "/users/import/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto importUserData(@PathVariable Integer userId,@RequestBody String jsonData);

    @GetMapping(value = APP_ROOT + "/users/export/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    String exportUserData(@PathVariable Integer userId);
}
