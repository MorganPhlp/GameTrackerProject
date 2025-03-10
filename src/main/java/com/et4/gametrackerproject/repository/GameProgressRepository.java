package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.model.GameProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameProgressRepository extends JpaRepository<GameProgress,Integer> {
}
