package com.et4.gametrackerproject.services;

import com.et4.gametrackerproject.dto.ReportDto;
import com.et4.gametrackerproject.enums.ReportStatus;
import com.et4.gametrackerproject.enums.ReportType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public interface ReportService {

    //Opérations de base
    ReportDto createReport(ReportDto reportDto);
    ReportDto updateReportDetails(Integer reportId, ReportDto reportDto);
    void deleteReport(Integer reportId);

    //Gestion du cycle de vie
    ReportDto resolveReport(Integer reportId, Integer adminId, String resolutionNotes);
    ReportDto changeReportStatus(Integer reportId, ReportStatus newStatus);

    //Récupération
    ReportDto getReportById(Integer reportId);
    Page<ReportDto> getReportsByStatus(ReportStatus status, Pageable pageable);
    Page<ReportDto> getReportsByType(ReportType type, Pageable pageable);
    Page<ReportDto> getUserReports(Integer userId, boolean isReporter, Pageable pageable);

    //Modération
    Page<ReportDto> getUnresolvedReports(Pageable pageable);
    ReportDto assignReportToAdmin(Integer reportId, Integer adminId);

    // Vérifications
    boolean hasPreviousReportsAgainstUser(Integer reporterId, Integer reportedUserId);
    boolean isContentAlreadyReported(Integer contentId, ReportType type);

    // Historique
    Page<ReportDto> getReportHistory(Integer contentId, ReportType type, Pageable pageable);
}