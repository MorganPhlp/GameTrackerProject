package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.model.GameCommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameCommentLikeRepository extends JpaRepository<GameCommentLike,Integer> {
}
