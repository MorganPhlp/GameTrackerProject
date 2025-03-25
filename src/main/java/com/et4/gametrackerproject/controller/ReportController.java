package com.et4.gametrackerproject.controller;

import com.et4.gametrackerproject.controller.api.ReportApi;
import com.et4.gametrackerproject.dto.ReportDto;
import com.et4.gametrackerproject.enums.ReportStatus;
import com.et4.gametrackerproject.enums.ReportType;
import com.et4.gametrackerproject.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController implements ReportApi {

    @Autowired
    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @Override
    public ReportDto createReport(ReportDto reportDto) {
        return reportService.createReport(reportDto);
    }

    @Override
    public ReportDto updateReportDetails(Integer reportId, ReportDto reportDto) {
        return reportService.updateReportDetails(reportId, reportDto);
    }

    @Override
    public void deleteReport(Integer reportId) {
        reportService.deleteReport(reportId);
    }

    @Override
    public ReportDto resolveReport(Integer reportId, Integer adminId, String resolutionNotes) {
        return reportService.resolveReport(reportId, adminId, resolutionNotes);
    }

    @Override
    public ReportDto changeReportStatus(Integer reportId, ReportStatus newStatus) {
        return reportService.changeReportStatus(reportId, newStatus);
    }

    @Override
    public ReportDto getReportById(Integer reportId) {
        return reportService.getReportById(reportId);
    }

    @Override
    public Page<ReportDto> getReportsByStatus(ReportStatus status, Pageable pageable) {
        return reportService.getReportsByStatus(status, pageable);
    }

    @Override
    public Page<ReportDto> getReportsByType(ReportType type, Pageable pageable) {
        return reportService.getReportsByType(type, pageable);
    }

    @Override
    public Page<ReportDto> getUserReports(Integer userId, boolean isReporter, Pageable pageable) {
        return reportService.getUserReports(userId, isReporter, pageable);
    }

    @Override
    public Page<ReportDto> getUnresolvedReports(Pageable pageable) {
        return reportService.getUnresolvedReports(pageable);
    }

    @Override
    public ReportDto assignReportToAdmin(Integer reportId, Integer adminId) {
        return reportService.assignReportToAdmin(reportId, adminId);
    }

    @Override
    public boolean hasPreviousReportsAgainstUser(Integer reporterId, Integer reportedUserId) {
        return reportService.hasPreviousReportsAgainstUser(reporterId, reportedUserId);
    }

    @Override
    public boolean isContentAlreadyReported(Integer contentId, ReportType type) {
        return reportService.isContentAlreadyReported(contentId, type);
    }

    @Override
    public Page<ReportDto> getReportHistory(Integer contentId, ReportType type, Pageable pageable) {
        return reportService.getReportHistory(contentId, type, pageable);
    }
}
