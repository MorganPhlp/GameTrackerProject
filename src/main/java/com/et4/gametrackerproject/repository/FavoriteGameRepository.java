package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.model.FavoriteGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteGameRepository extends JpaRepository<FavoriteGame,Integer> {
}
