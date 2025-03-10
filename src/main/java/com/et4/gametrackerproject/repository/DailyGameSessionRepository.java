package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.model.DailyGameSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyGameSessionRepository extends JpaRepository<DailyGameSession,Integer> {
}
