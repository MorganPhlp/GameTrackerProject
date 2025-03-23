package com.et4.gametrackerproject.services.impl;

import com.et4.gametrackerproject.dto.UserDto;
import com.et4.gametrackerproject.enums.FriendshipStatus;
import com.et4.gametrackerproject.enums.OnlineStatus;
import com.et4.gametrackerproject.enums.PrivacySetting;
import com.et4.gametrackerproject.enums.ScreenTheme;
import com.et4.gametrackerproject.exception.EntityNotFoundException;
import com.et4.gametrackerproject.exception.ErrorCodes;
import com.et4.gametrackerproject.exception.InvalidEntityException;
import com.et4.gametrackerproject.model.User;
import com.et4.gametrackerproject.repository.AvatarRepository;
import com.et4.gametrackerproject.repository.FriendshipRepository;
import com.et4.gametrackerproject.repository.UserRepository;
import com.et4.gametrackerproject.services.UserService;
import com.et4.gametrackerproject.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AvatarRepository avatarRepository;
    private final FriendshipRepository friendshipRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AvatarRepository avatarRepository, FriendshipRepository friendshipRepository) {
        this.userRepository = userRepository;
        this.avatarRepository = avatarRepository;
        this.friendshipRepository = friendshipRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        List<String> errors = UserValidator.validate(userDto);
        if (!errors.isEmpty()) {
            log.error("User is not valid: {}", errors);
            throw new InvalidEntityException("User is not valid", ErrorCodes.USER_NOT_VALID,errors);
        }

        log.info("Create User {}", userDto);

        return UserDto.fromEntity(userRepository.save(UserDto.toEntity(userDto)));
    }

    @Override
    public UserDto updateUser(Integer userId, UserDto userDto) {
        List<String> errors = UserValidator.validate(userDto);
        if (!errors.isEmpty()) {
            log.error("User is not valid: {}", errors);
            throw new InvalidEntityException("User is not valid", ErrorCodes.USER_NOT_VALID, errors);
        }
        if(!userRepository.existsById(userId)) {
            log.error("User with id {} not found", userId);
            throw new EntityNotFoundException("User with id " + userId + " not found", ErrorCodes.USER_NOT_FOUND);
        }
        if(!userId.equals(userDto.getId())) {
            log.error("User id {} is not the same as userDto id {}", userId, userDto.getId());
            throw new EntityNotFoundException("User id " + userId + " is not the same as userDto id " + userDto.getId(), ErrorCodes.USER_NOT_FOUND);
        }

        log.info("Update User {}", userDto);

        return UserDto.fromEntity(userRepository.save(UserDto.toEntity(userDto)));
    }

    @Override
    public void deleteUser(Integer userId) {
        if(userId == null) {
            log.error("User id is null");
            throw new EntityNotFoundException("User id is null", ErrorCodes.USER_NOT_FOUND);
        }
        if(!userRepository.existsById(userId)) {
            log.error("User with id {} not found", userId);
            throw new EntityNotFoundException("User with id " + userId + " not found", ErrorCodes.USER_NOT_FOUND);
        }

        log.info("Delete User with id {}", userId);

        // TODO : Verifier que les relations de cet utilisateur sont bien supprimées avant de le supprimer
        userRepository.deleteById(userId);
    }



    @Override
    public UserDto getUserById(Integer userId) {
        if(userId == null) {
            log.error("User id is null");
            throw new EntityNotFoundException("User id is null", ErrorCodes.USER_NOT_FOUND);
        }

        log.info("Get User with id {}", userId);

        return userRepository.findById(userId)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found", ErrorCodes.USER_NOT_FOUND));
    }

    @Override
    public UserDto getUserByUsername(String username) {
        if(username == null) {
            log.error("Username is null");
            throw new EntityNotFoundException("Username is null", ErrorCodes.USER_NOT_FOUND);
        }

        log.info("Get User with username {}", username);

        return userRepository.findByUsername(username)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("User with username " + username + " not found", ErrorCodes.USER_NOT_FOUND));
    }

    @Override
    public UserDto getUserByEmail(String email) {
        if(email == null) {
            log.error("Email is null");
            throw new EntityNotFoundException("Email is null", ErrorCodes.USER_NOT_FOUND);
        }

        log.info("Get User with email {}", email);

        return userRepository.findByEmail(email)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + email + " not found", ErrorCodes.USER_NOT_FOUND));
    }

    @Override
    public Page<UserDto> getAllUsers(Pageable pageable) {
        log.info("Get all Users");

        return userRepository.findAll(pageable).map(UserDto::fromEntity);
    }

    // TODO : Ajouter la logique de hashage du mot de passe et du choix de mot de passe
    @Override
    public void resetPassword(Integer userId, String newPassword) {
        if(userId == null) {
            log.error("User id is null");
            throw new EntityNotFoundException("User id is null", ErrorCodes.USER_NOT_FOUND);
        }
        if(newPassword == null) {
            log.error("New password is null");
            throw new InvalidEntityException("New password is null", ErrorCodes.USER_NOT_VALID);
        }
        if(!userRepository.existsById(userId)) {
            log.error("User with id {} not found", userId);
            throw new EntityNotFoundException("User with id " + userId + " not found", ErrorCodes.USER_NOT_FOUND);
        }

        log.info("Reset password for User with id {}", userId);

        // TODO : Hasher le mot de passe avant de le sauvegarder
        userRepository.updateUserPassword(userId, newPassword);
    }

    @Override
    public void requestPasswordReset(String email) {
        if(email == null) {
            log.error("Email is null");
            throw new InvalidEntityException("Email is null", ErrorCodes.USER_NOT_VALID);
        }
        if(!userRepository.existsByEmail(email)) {
            log.error("User with email {} not found", email);
            throw new EntityNotFoundException("User with email " + email + " not found", ErrorCodes.USER_NOT_FOUND);
        }

        log.info("Request password reset for User with email {}", email);

        // TODO : Envoyer un email de réinitialisation de mot de passe
    }

    @Override
    public UserDto updatePrivacySettings(Integer userId, PrivacySetting privacy) {
        if(userId == null) {
            log.error("User id is null");
            throw new EntityNotFoundException("User id is null", ErrorCodes.USER_NOT_FOUND);
        }
        if(privacy == null) {
            log.error("Privacy setting is null");
            throw new InvalidEntityException("Privacy setting is null", ErrorCodes.USER_NOT_VALID);
        }
        if(!userRepository.existsById(userId)) {
            log.error("User with id {} not found", userId);
            throw new EntityNotFoundException("User with id " + userId + " not found", ErrorCodes.USER_NOT_FOUND);
        }

        log.info("Update privacy settings for User with id {}", userId);

        userRepository.updateUserPrivacySetting(userId, privacy);
        return getUserById(userId);
    }

    @Override
    public UserDto updateThemePreference(Integer userId, ScreenTheme theme) {
        if(userId == null) {
            log.error("User id is null");
            throw new EntityNotFoundException("User id is null", ErrorCodes.USER_NOT_FOUND);
        }
        if(theme == null) {
            log.error("Theme preference is null");
            throw new InvalidEntityException("Theme preference is null", ErrorCodes.USER_NOT_VALID);
        }
        if(!userRepository.existsById(userId)) {
            log.error("User with id {} not found", userId);
            throw new EntityNotFoundException("User with id " + userId + " not found", ErrorCodes.USER_NOT_FOUND);
        }

        log.info("Update theme preference for User with id {}", userId);

        userRepository.updateUserTheme(userId, theme);
        return getUserById(userId);
    }

    @Override
    public UserDto updateAvatar(Integer userId, Integer avatarId) {
        if(userId == null) {
            log.error("User id is null");
            throw new EntityNotFoundException("User id is null", ErrorCodes.USER_NOT_FOUND);
        }
        if(avatarId == null) {
            log.error("Avatar id is null");
            throw new InvalidEntityException("Avatar id is null", ErrorCodes.USER_NOT_VALID);
        }
        if(!userRepository.existsById(userId)) {
            log.error("User with id {} not found", userId);
            throw new EntityNotFoundException("User with id " + userId + " not found", ErrorCodes.USER_NOT_FOUND);
        }
        if(!avatarRepository.existsById(avatarId)) {
            log.error("Avatar with id {} not found", avatarId);
            throw new EntityNotFoundException("Avatar with id " + avatarId + " not found", ErrorCodes.AVATAR_NOT_FOUND);
        }

        log.info("Update avatar for User with id {}", userId);

        userRepository.updateUserAvatar(userId, avatarId);
        return getUserById(userId);
    }

    @Override
    public Page<UserDto> searchUsers(String query, Pageable pageable) {
        if(query == null) {
            log.error("Query is null");
            throw new InvalidEntityException("Query is null", ErrorCodes.USER_NOT_VALID);
        }

        log.info("Search Users with query {}", query);

        return userRepository.findByUsernameContainingIgnoreCase(query, pageable).map(UserDto::fromEntity);
    }

    @Override
    public Page<UserDto> getFriendsList(Integer userId, Pageable pageable) {
        if(userId == null) {
            log.error("User id is null");
            throw new EntityNotFoundException("User id is null", ErrorCodes.USER_NOT_FOUND);
        }
        if(!userRepository.existsById(userId)) {
            log.error("User with id {} not found", userId);
            throw new EntityNotFoundException("User with id " + userId + " not found", ErrorCodes.USER_NOT_FOUND);
        }

        log.info("Get Friends List for User with id {}", userId);

        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found", ErrorCodes.USER_NOT_FOUND));

        return friendshipRepository.findAllUsersByUserAndStatus(user, FriendshipStatus.ACCEPTED, pageable).map(UserDto::fromEntity);
    }

    @Override
    public UserDto updatePlayStats(Integer userId, Integer gameTime, Integer gamesPlayed) {
        if(userId == null) {
            log.error("User id is null");
            throw new EntityNotFoundException("User id is null", ErrorCodes.USER_NOT_FOUND);
        }
        if(gameTime == null) {
            log.error("Game time is null");
            throw new InvalidEntityException("Game time is null", ErrorCodes.USER_NOT_VALID);
        }
        if(gamesPlayed == null) {
            log.error("Games played is null");
            throw new InvalidEntityException("Games played is null", ErrorCodes.USER_NOT_VALID);
        }
        if(!userRepository.existsById(userId)) {
            log.error("User with id {} not found", userId);
            throw new EntityNotFoundException("User with id " + userId + " not found", ErrorCodes.USER_NOT_FOUND);
        }

        log.info("Update play stats for User with id {}", userId);

        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found", ErrorCodes.USER_NOT_FOUND));
        user.setTotalPlayTime(user.getTotalPlayTime() + gameTime);
        user.setTotalGamesPlayed(user.getTotalGamesPlayed() + gamesPlayed);
        return UserDto.fromEntity(userRepository.save(user));
    }

    @Override
    public UserDto addPoints(Integer userId, Integer points) {
        if(userId == null) {
            log.error("User id is null");
            throw new EntityNotFoundException("User id is null", ErrorCodes.USER_NOT_FOUND);
        }
        if(points == null) {
            log.error("Points is null");
            throw new InvalidEntityException("Points is null", ErrorCodes.USER_NOT_VALID);
        }
        if(!userRepository.existsById(userId)) {
            log.error("User with id {} not found", userId);
            throw new EntityNotFoundException("User with id " + userId + " not found", ErrorCodes.USER_NOT_FOUND);
        }

        log.info("Add points for User with id {}", userId);

        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found", ErrorCodes.USER_NOT_FOUND));
        user.setPoints(user.getPoints() + points);
        return UserDto.fromEntity(userRepository.save(user));
    }

    @Override
    public Page<UserDto> getUsersByStatus(boolean isActive, Pageable pageable) {
        log.info("Get Users by status {}", isActive);

        return userRepository.findByIsActive(isActive, pageable).map(UserDto::fromEntity);
    }

    @Override
    public UserDto updateOnlineStatus(Integer userId, OnlineStatus status) {
        if(userId == null) {
            log.error("User id is null");
            throw new EntityNotFoundException("User id is null", ErrorCodes.USER_NOT_FOUND);
        }
        if(status == null) {
            log.error("Online status is null");
            throw new InvalidEntityException("Online status is null", ErrorCodes.USER_NOT_VALID);
        }
        if(!userRepository.existsById(userId)) {
            log.error("User with id {} not found", userId);
            throw new EntityNotFoundException("User with id " + userId + " not found", ErrorCodes.USER_NOT_FOUND);
        }

        log.info("Update online status for User with id {}", userId);

        userRepository.updateUserStatus(userId, status);
        return getUserById(userId);
    }

    @Override
    public UserDto recordLogin(Integer userId) {
        if(userId == null) {
            log.error("User id is null");
            throw new EntityNotFoundException("User id is null", ErrorCodes.USER_NOT_FOUND);
        }
        if(!userRepository.existsById(userId)) {
            log.error("User with id {} not found", userId);
            throw new EntityNotFoundException("User with id " + userId + " not found", ErrorCodes.USER_NOT_FOUND);
        }

        log.info("Record login for User with id {}", userId);

        userRepository.updateUserStatus(userId, OnlineStatus.ONLINE);
        return getUserById(userId);
    }

    @Override
    public boolean isUsernameAvailable(String username) {
        if(username == null) {
            log.error("Username is null");
            throw new InvalidEntityException("Username is null", ErrorCodes.USER_NOT_VALID);
        }

        log.info("Check if username {} is available", username);

        return !userRepository.existsByUsername(username);
    }

    @Override
    public boolean isEmailRegistered(String email) {
        if(email == null) {
            log.error("Email is null");
            throw new InvalidEntityException("Email is null", ErrorCodes.USER_NOT_VALID);
        }

        log.info("Check if email {} is registered", email);

        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean isAdultUser(Integer userId) {
        if(userId == null) {
            log.error("User id is null");
            throw new EntityNotFoundException("User id is null", ErrorCodes.USER_NOT_FOUND);
        }

        log.info("Check if User with id {} is adult", userId);

        return userRepository.findById(userId)
                .map(user -> user.getBirthDate().isBefore(user.getBirthDate().plusYears(18)))
                .orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found", ErrorCodes.USER_NOT_FOUND));
    }

    @Override
    public UserDto shareProfile(Integer userId, String platform) {
        if(userId == null) {
            log.error("User id is null");
            throw new EntityNotFoundException("User id is null", ErrorCodes.USER_NOT_FOUND);
        }
        if(platform == null) {
            log.error("Platform is null");
            throw new InvalidEntityException("Platform is null", ErrorCodes.USER_NOT_VALID);
        }
        if(!userRepository.existsById(userId)) {
            log.error("User with id {} not found", userId);
            throw new EntityNotFoundException("User with id " + userId + " not found", ErrorCodes.USER_NOT_FOUND);
        }

        log.info("Share profile for User with id {} on platform {}", userId, platform);

        // TODO : Partager le profil de l'utilisateur sur la plateforme choisie
        return getUserById(userId);
    }

    @Override
    public UserDto importUserData(Integer userId, String jsonData) {
        if(userId == null) {
            log.error("User id is null");
            throw new EntityNotFoundException("User id is null", ErrorCodes.USER_NOT_FOUND);
        }
        if(jsonData == null) {
            log.error("Json data is null");
            throw new InvalidEntityException("Json data is null", ErrorCodes.USER_NOT_VALID);
        }
        if(!userRepository.existsById(userId)) {
            log.error("User with id {} not found", userId);
            throw new EntityNotFoundException("User with id " + userId + " not found", ErrorCodes.USER_NOT_FOUND);
        }

        log.info("Import user data for User with id {}", userId);

        // TODO : Importer les données de l'utilisateur depuis le fichier JSON
        return getUserById(userId);
    }

    @Override
    public String exportUserData(Integer userId) {
        if(userId == null) {
            log.error("User id is null");
            throw new EntityNotFoundException("User id is null", ErrorCodes.USER_NOT_FOUND);
        }
        if(!userRepository.existsById(userId)) {
            log.error("User with id {} not found", userId);
            throw new EntityNotFoundException("User with id " + userId + " not found", ErrorCodes.USER_NOT_FOUND);
        }

        log.info("Export user data for User with id {}", userId);

        // TODO : Exporter les données de l'utilisateur dans un fichier JSON
        return "";
    }
}
