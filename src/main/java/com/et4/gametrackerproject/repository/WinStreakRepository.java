package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.model.Game;
import com.et4.gametrackerproject.model.User;
import com.et4.gametrackerproject.model.WinStreak;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface WinStreakRepository extends JpaRepository<WinStreak,Integer> {

    // Requêtes de base
    List<WinStreak> findByUser(User user);

    List<WinStreak> findByGame(Game game);

    Optional<WinStreak> findByUserAndGame(User user, Game game);

    Optional<WinStreak> findByUserIdAndGameId(Integer userId, Integer gameId);

    List<WinStreak> findByCurrentStreakGreaterThan(Integer minStreak);

    List<WinStreak> findByBestStreakGreaterThan(Integer minStreak);

    List<WinStreak> findByLastWinAfter(Instant date);

    List<WinStreak> findByLastWinIsNotNull();

    // Requêtes paginées
    Page<WinStreak> findByUser(User user, Pageable pageable);

    Page<WinStreak> findByGame(Game game, Pageable pageable);

    Page<WinStreak> findByCurrentStreakGreaterThan(Integer minStreak, Pageable pageable);

    Page<WinStreak> findByBestStreakGreaterThan(Integer minStreak, Pageable pageable);

    // Requêtes combinées
    List<WinStreak> findByUserAndCurrentStreakGreaterThan(User user, Integer minStreak);

    List<WinStreak> findByUserAndBestStreakGreaterThanEqual(User user, Integer minStreak);

    List<WinStreak> findByGameAndCurrentStreakGreaterThan(Game game, Integer minStreak);

    List<WinStreak> findByGameAndBestStreakGreaterThanEqual(Game game, Integer minStreak);

    // Requêtes pour trouver les meilleurs streaks
    List<WinStreak> findTop10ByOrderByCurrentStreakDesc();

    List<WinStreak> findTop10ByOrderByBestStreakDesc();

    List<WinStreak> findTop10ByGameOrderByCurrentStreakDesc(Game game);

    List<WinStreak> findTop10ByGameOrderByBestStreakDesc(Game game);

    List<WinStreak> findTop10ByUserOrderByCurrentStreakDesc(User user);

    List<WinStreak> findTop10ByUserOrderByBestStreakDesc(User user);

    // Méthodes de mise à jour des streaks
    @Modifying
    @Transactional
    @Query("UPDATE WinStreak w SET w.currentStreak = w.currentStreak + 1, " +
            "w.bestStreak = CASE WHEN w.currentStreak + 1 > w.bestStreak THEN w.currentStreak + 1 ELSE w.bestStreak END, " +
            "w.lastWin = CURRENT_TIMESTAMP " +
            "WHERE w.user.id = :userId AND w.game.id = :gameId")
    int incrementWinStreak(@Param("userId") Integer userId, @Param("gameId") Integer gameId);

    @Modifying
    @Transactional
    @Query("UPDATE WinStreak w SET w.currentStreak = 1, w.lastWin = CURRENT_TIMESTAMP " +
            "WHERE w.user.id = :userId AND w.game.id = :gameId")
    int resetWinStreak(@Param("userId") Integer userId, @Param("gameId") Integer gameId);

    @Modifying
    @Transactional
    @Query("UPDATE WinStreak w SET w.currentStreak = 0 " +
            "WHERE w.user.id = :userId AND w.game.id = :gameId")
    int endWinStreak(@Param("userId") Integer userId, @Param("gameId") Integer gameId);

    // Requêtes pour trouver les utilisateurs avec les meilleurs streaks

    @Query("SELECT w.user, MAX(w.currentStreak) as maxStreak FROM WinStreak w WHERE w.game = :game GROUP BY w.user ORDER BY maxStreak DESC")
    List<Object[]> findUsersWithHighestCurrentStreakByGame(@Param("game") Game game, Pageable pageable);

    @Query("SELECT w.user, MAX(w.bestStreak) as maxStreak FROM WinStreak w WHERE w.game = :game GROUP BY w.user ORDER BY maxStreak DESC")
    List<Object[]> findUsersWithHighestBestStreakByGame(@Param("game") Game game, Pageable pageable);
}
