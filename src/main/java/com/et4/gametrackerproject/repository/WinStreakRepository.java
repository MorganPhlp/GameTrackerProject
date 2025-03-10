package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.model.WinStreak;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WinStreakRepository extends JpaRepository<WinStreak,Integer> {
}
