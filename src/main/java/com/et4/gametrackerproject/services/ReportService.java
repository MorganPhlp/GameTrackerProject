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
    void mergeDuplicateReports(List<Integer> reportIds);

    // Vérifications
    boolean hasPreviousReportsAgainstUser(Integer reporterId, Integer reportedUserId);
    boolean isContentAlreadyReported(Integer contentId, ReportType type);

    //Intégration
    void applyReportSanctions(Integer reportId);
    void notifyParties(Integer reportId);
    void hideReportedContent(Integer reportId);

    // Historique
    Page<ReportDto> getReportHistory(Integer contentId, ReportType type, Pageable pageable);
    List<ReportDto> getReportResolutionHistory(Integer reportId);
    Page<ReportDto> getAdminActivity(Integer adminId, Pageable pageable);

    // Administration
    void exportReports(String outputPath, ReportStatus status);
    Page<ReportDto> searchReports(String searchQuery, Pageable pageable);
}