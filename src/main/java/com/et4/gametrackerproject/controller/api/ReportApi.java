package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.ReportDto;
import com.et4.gametrackerproject.enums.ReportStatus;
import com.et4.gametrackerproject.enums.ReportType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

public interface ReportApi {

    //Opérations de base

    @PostMapping(value = APP_ROOT + "/report/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ReportDto createReport(@RequestBody ReportDto reportDto);

    @PutMapping(value = APP_ROOT + "/report/{reportId}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ReportDto updateReportDetails(@PathVariable Integer reportId,@RequestBody ReportDto reportDto);

    @DeleteMapping(value = APP_ROOT + "/report/{reportId}")
    void deleteReport(@PathVariable Integer reportId);

    //Gestion du cycle de vie

    @PutMapping(value = APP_ROOT + "/report/{reportId}/resolve", produces = MediaType.APPLICATION_JSON_VALUE)
    ReportDto resolveReport(@PathVariable Integer reportId,@RequestBody Integer adminId,@RequestBody String resolutionNotes);

    @PutMapping(value = APP_ROOT + "/report/{reportId}/changeStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    ReportDto changeReportStatus(@PathVariable Integer reportId,@RequestBody ReportStatus newStatus);

    //Récupération

    @GetMapping(value = APP_ROOT + "/report/{reportId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ReportDto getReportById(@PathVariable Integer reportId);

    @GetMapping(value = APP_ROOT + "/report/status/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<ReportDto> getReportsByStatus(@PathVariable ReportStatus status, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/report/type/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<ReportDto> getReportsByType(@PathVariable ReportType type, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/report/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<ReportDto> getUserReports(@PathVariable Integer userId,@RequestBody boolean isReporter, Pageable pageable);

    //Modération

    @GetMapping(value = APP_ROOT + "/report/unresolved", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<ReportDto> getUnresolvedReports(Pageable pageable);

    @PutMapping(value = APP_ROOT + "/report/{reportId}/assign", produces = MediaType.APPLICATION_JSON_VALUE)
    ReportDto assignReportToAdmin(@PathVariable Integer reportId,@RequestBody Integer adminId);

    @PutMapping(value = APP_ROOT + "/report/merge", consumes = MediaType.APPLICATION_JSON_VALUE)
    void mergeDuplicateReports(@RequestBody List<Integer> reportIds);

    // Vérifications

    @GetMapping(value = APP_ROOT + "/report/previous", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean hasPreviousReportsAgainstUser(@RequestBody Integer reporterId,@RequestBody Integer reportedUserId);

    @GetMapping(value = APP_ROOT + "/report/content", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean isContentAlreadyReported(@RequestBody Integer contentId,@RequestBody ReportType type);

    //Intégration

    @PutMapping(value = APP_ROOT + "/report/{reportId}/applySanctions")
    void applyReportSanctions(@PathVariable Integer reportId);

    @PutMapping(value = APP_ROOT + "/report/{reportId}/notifyParties")
    void notifyParties(@PathVariable Integer reportId);

    @PutMapping(value = APP_ROOT + "/report/{reportId}/hideContent")
    void hideReportedContent(@PathVariable Integer reportId);

    // Historique

    @GetMapping(value = APP_ROOT + "/report/{contentId}/{type}/history", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<ReportDto> getReportHistory(@PathVariable Integer contentId,@PathVariable ReportType type, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/report/{reportId}/resolutionHistory", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ReportDto> getReportResolutionHistory(@PathVariable Integer reportId);

    @GetMapping(value = APP_ROOT + "/report/admin/{adminId}/activity", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<ReportDto> getAdminActivity(@PathVariable Integer adminId, Pageable pageable);

    // Administration

    @PutMapping(value = APP_ROOT + "/report/export", produces = MediaType.APPLICATION_JSON_VALUE)
    void exportReports(@RequestBody String outputPath,@RequestBody ReportStatus status);

    @GetMapping(value = APP_ROOT + "/report/search", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<ReportDto> searchReports(@RequestBody String searchQuery, Pageable pageable);
}
