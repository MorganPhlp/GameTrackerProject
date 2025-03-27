package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.model.User;
import com.et4.gametrackerproject.model.WinStreak;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WinStreakRepository extends JpaRepository<WinStreak,Integer> {

    // Requêtes de base
    List<WinStreak> findByUser(User user);

    List<WinStreak> findByGameId(Integer gameId);

    Optional<WinStreak> findByUserIdAndGameId(Integer userId, Integer gameId);

    Page<WinStreak> findByUser(User user, Pageable pageable);

    Page<WinStreak> findByUserId(Integer userId, Pageable pageable);

    Page<WinStreak> findByGameId(Integer gameId, Pageable pageable);

    Page<WinStreak> findByCurrentStreakGreaterThan(Integer currentStreakIsGreaterThan, Pageable pageable);

    @Query(value = "SELECT * FROM WinStreak ORDER BY best_streak DESC LIMIT :limit", nativeQuery = true)
    List<WinStreak> findTopBestStreaks(@Param("limit") int limit);

    @Query(value = "SELECT * FROM WinStreak WHERE game_id = :gameId ORDER BY best_streak DESC LIMIT :limit", nativeQuery = true)
    List<WinStreak> findTopBestStreaksByGame(@Param("gameId") Integer gameId,@Param("limit") int limit);
}
