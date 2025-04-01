package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.enums.OnlineStatus;
import com.et4.gametrackerproject.enums.PrivacySetting;
import com.et4.gametrackerproject.enums.ProfilRank;
import com.et4.gametrackerproject.enums.ScreenTheme;
import com.et4.gametrackerproject.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    // Requêtes de base pour l'authentification et la gestion des utilisateurs
    Optional<User> findById(int id);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    boolean existsByEmail(String email);

    Page<User> findByIsActive(boolean isActive, Pageable pageable);

    boolean existsByUsername(String username);

    List<User> findByIsActive(Boolean isActive);

    List<User> findByIsAdmin(Boolean isAdmin);

    Page<User> findByIsAdmin(Boolean isAdmin, Pageable pageable);

    // Recherches avancées
    List<User> findByUsernameContainingIgnoreCase(String username);

    Page<User> findByUsernameContainingIgnoreCase(String username, Pageable pageable);

    List<User> findByOnlineStatus(OnlineStatus status);

    // Mise à jour des utilisateurs
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.onlineStatus = :status, u.lastLogin = CURRENT_TIMESTAMP WHERE u.id = :userId")
    int updateUserStatus(@Param("userId") Integer userId, @Param("status") OnlineStatus status);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.isActive = :isActive WHERE u.id = :userId")
    int updateUserActiveStatus(@Param("userId") Integer userId, @Param("isActive") Boolean isActive);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.privacySetting = :privacySetting WHERE u.id = :userId")
    void updateUserPrivacySetting(@Param("userId") Integer userId, @Param("privacySetting") PrivacySetting privacySetting);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.themePreference = :theme WHERE u.id = :userId")
    void updateUserTheme(@Param("userId") Integer userId, @Param("theme") ScreenTheme theme);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password = :newPassword WHERE u.id = :userId")
    void updateUserPassword(@Param("userId") Integer userId, @Param("newPassword") String newPassword);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.avatar.id = :avatarId WHERE u.id = :userId")
    void updateUserAvatar(@Param("userId") Integer userId, @Param("avatarId") Integer avatarId);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.isAdmin = :isAdmin WHERE u.id = :userId")
    int updateUserAdminStatus(@Param("userId") Integer userId, @Param("isAdmin") Boolean isAdmin);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.userRank = :rank WHERE u.id = :userId")
    int updateUserRank(@Param("userId") Integer userId, @Param("rank") ProfilRank rank);

    // Statistiques des utilisateurs
    @Query("SELECT AVG(u.totalGamesPlayed) FROM User u WHERE u.isActive = true")
    Double getAverageGamesPlayedByActiveUsers();

    @Query("SELECT AVG(u.totalPlayTime) FROM User u WHERE u.isActive = true")
    Double getAveragePlayTimeByActiveUsers();

    @Query("SELECT SUM(u.totalPlayTime) FROM User u")
    Long getTotalPlayTimeAcrossAllUsers();

    @Query("SELECT AVG(u.points) FROM User u WHERE u.isActive = true")
    Double getAveragePointsByActiveUsers();

    @Query("SELECT u FROM User u WHERE u.totalGamesPlayed > :minGames ORDER BY u.points DESC")
    List<User> findTopUsersByPoints(@Param("minGames") Integer minGames, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.totalGamesPlayed > :minGames ORDER BY u.totalPlayTime DESC")
    List<User> findTopUsersByPlayTime(@Param("minGames") Integer minGames, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.totalGamesPlayed > :minGames ORDER BY u.totalGamesPlayed DESC")
    List<User> findTopUsersByGamesPlayed(@Param("minGames") Integer minGames, Pageable pageable);

    @Query("SELECT COUNT(u) FROM User u WHERE u.userRank = :rank")
    Long countUsersByRank(@Param("rank") ProfilRank rank);

    // Trouver les amis des utilisateurs
    @Query("SELECT u2 FROM User u1 JOIN Friendship f ON (u1 = f.user1 OR u1 = f.user2) JOIN User u2 ON (u2 = f.user1 OR u2 = f.user2) " +
            "WHERE u1.id = :userId AND u2.id <> :userId AND f.status = 'ACCEPTED'")
    List<User> findFriendsByUserId(@Param("userId") Integer userId);

    @Query("SELECT u2 FROM User u1 JOIN Friendship f ON (u1 = f.user1 OR u1 = f.user2) JOIN User u2 ON (u2 = f.user1 OR u2 = f.user2) " +
            "WHERE u1.id = :userId AND u2.id <> :userId AND f.status = 'ACCEPTED'")
    Page<User> findFriendsByUserId(@Param("userId") Integer userId, Pageable pageable);

    @Query("SELECT COUNT(f) FROM Friendship f WHERE (f.user1.id = :userId OR f.user2.id = :userId) AND f.status = 'ACCEPTED'")
    Long countFriendsByUserId(@Param("userId") Integer userId);

    // Rechercher les utilisateurs par sanctionsReceived et par sanctionsDistributed
    @Query("SELECT u FROM User u JOIN u.sanctionsDistributed s " +
            "JOIN u.sanctionsReceived sr " +
            "WHERE s.id = :sanctionId OR sr.id = :sanctionId")
    Optional<User> findByUserSanctionId(@Param("sanctionId") Integer sanctionId);

    //trouver des utilisateurs par avatar
    @Query("SELECT u FROM User u WHERE u.avatar.id = :avatarId")
    Optional<User> findByAvatarId(Integer id);

    //trouver des utilisateurs par session de jeu
    @Query("SELECT u FROM User u JOIN DailyGameSession gs ON u.id = gs.user.id WHERE gs.id = :sessionId")
    Optional<User> findByDailyGameSessionId(Integer id);

}