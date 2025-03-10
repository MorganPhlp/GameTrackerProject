package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.model.GameRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRatingRepository extends JpaRepository<GameRating,Integer> {
}
