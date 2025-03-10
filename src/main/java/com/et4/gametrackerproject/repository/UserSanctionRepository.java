package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.model.UserSanction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSanctionRepository extends JpaRepository<UserSanction,Integer> {
}
