package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.model.GameTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameTagRepository extends JpaRepository<GameTag,Integer> {
}
