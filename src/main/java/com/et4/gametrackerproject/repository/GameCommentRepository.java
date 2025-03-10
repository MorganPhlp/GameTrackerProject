package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.model.GameComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameCommentRepository extends JpaRepository<GameComment,Integer> {
}
