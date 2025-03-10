package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
