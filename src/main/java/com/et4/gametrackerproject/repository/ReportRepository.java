package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,Integer> {
}
