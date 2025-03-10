package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.model.GameLeaderboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameLeaderboardRepository extends JpaRepository<GameLeaderboard,Integer> {
}
