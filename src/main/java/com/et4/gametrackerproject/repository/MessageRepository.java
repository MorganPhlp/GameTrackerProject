package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Integer> {
}
