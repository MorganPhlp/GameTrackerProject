package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game,Integer> {
}
