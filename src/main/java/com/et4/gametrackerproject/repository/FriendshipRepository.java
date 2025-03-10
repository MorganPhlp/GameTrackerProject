package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendshipRepository extends JpaRepository<Friendship,Integer> {
}
