package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.GameLeaderboardDto;
import com.et4.gametrackerproject.dto.UserDto;
import com.et4.gametrackerproject.enums.LeaderboardPeriod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

public interface GameLeaderboardApi {

    //Opérations de base

    @PostMapping(value = APP_ROOT + "/leaderboard/submit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    GameLeaderboardDto submitScore(@RequestBody GameLeaderboardDto scoreEntry);

    @PutMapping(value = APP_ROOT + "/leaderboard/update/{entryId}/{newScore}", produces = MediaType.APPLICATION_JSON_VALUE)
    GameLeaderboardDto updateScore(@PathVariable Integer entryId,@PathVariable Integer newScore);

    @DeleteMapping(value = APP_ROOT + "/leaderboard/delete/{entryId}")
    void deleteScoreEntry(@PathVariable Integer entryId);

    //Récupération des classements

    @GetMapping(value = APP_ROOT + "/leaderboard/game/{gameId}/{period}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameLeaderboardDto> getLeaderboardForGame(@PathVariable Integer gameId,@PathVariable LeaderboardPeriod period, Pageable pageable);

    @PutMapping(value = APP_ROOT + "/leaderboard/reset/{gameId}/{period}", produces = MediaType.APPLICATION_JSON_VALUE)
    void resetLeaderboard(@PathVariable Integer gameId,@PathVariable LeaderboardPeriod period);
}
