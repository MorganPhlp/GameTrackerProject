package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.GameDto;
import com.et4.gametrackerproject.enums.DifficultyLevel;
import com.et4.gametrackerproject.enums.GameCategory;
import com.et4.gametrackerproject.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

public interface GameApi {

    // Opérations de base

    @PostMapping(value = APP_ROOT + "/game/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    GameDto createGame(@RequestBody GameDto gameDto);

    @PutMapping(value = APP_ROOT + "/game/{gameId}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    GameDto updateGame(@PathVariable("gameId") Integer id,@RequestBody GameDto gameDto);

    @DeleteMapping(value = APP_ROOT + "/game/{gameId}")
    void deleteGame(@PathVariable("gameId") Integer id);

    @GetMapping(value = APP_ROOT + "/game/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    GameDto getGameById(@PathVariable("gameId") Integer id);

    // Récupération

    @GetMapping(value = APP_ROOT + "/game/all", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameDto> getAllGames(Pageable pageable);

    @GetMapping(value = APP_ROOT + "/game/search", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameDto> searchGames(@RequestBody String query, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/game/ids", produces = MediaType.APPLICATION_JSON_VALUE)
    List<GameDto> getGamesByIds(@RequestBody List<Integer> ids);

    //Filtrage

    @GetMapping(value = APP_ROOT + "/game/category", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameDto> filterByCategory(@RequestBody GameCategory category, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/game/difficulty", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameDto> filterByDifficulty(@RequestBody DifficultyLevel difficulty, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/game/age", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameDto> filterByAgeRange(@RequestBody Integer minAge,@RequestBody Integer maxAge, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/game/tags", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameDto> filterByTags(@RequestBody Set<String> tags, Pageable pageable);

    //Gestion des métadonnées

    @GetMapping(value = APP_ROOT + "/game/{gameId}/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
    GameDto updateGameStatistics(@PathVariable Integer gameId);

    @PutMapping(value = APP_ROOT + "/game/{gameId}/average", produces = MediaType.APPLICATION_JSON_VALUE)
    void updateAverageRating(@PathVariable Integer gameId);

    // Relations

    @PutMapping(value = APP_ROOT + "/game/{gameId}/tag", produces = MediaType.APPLICATION_JSON_VALUE)
    GameDto addTagToGame(@PathVariable Integer gameId,@RequestBody Tag tag);

    @DeleteMapping(value = APP_ROOT + "/game/{gameId}/tag", produces = MediaType.APPLICATION_JSON_VALUE)
    GameDto removeTagFromGame(@PathVariable Integer gameId,@RequestBody Tag tag);

    @GetMapping(value = APP_ROOT + "/game/{gameId}/similar", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameDto> getSimilarGames(@PathVariable Integer gameId, Pageable pageable);

    // Statistiques

    @GetMapping(value = APP_ROOT + "/game/category/distribution", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<GameCategory, Long> getGameDistributionByCategory();

    @GetMapping(value = APP_ROOT + "/game/difficulty/distribution", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<DifficultyLevel, Long> getGameDistributionByDifficulty();

    @GetMapping(value = APP_ROOT + "/game/{gameId}/activity", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Long> getPlayActivityTrends(@PathVariable Integer gameId);

    @GetMapping(value = APP_ROOT + "/game/{gameId}/performance", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> getGamePerformanceReport(@PathVariable Integer gameId);

    // Administration

    @PutMapping(value = APP_ROOT + "/game/status", produces = MediaType.APPLICATION_JSON_VALUE)
    void batchUpdateGamesStatus(@RequestBody List<Integer> gameIds,@RequestBody boolean isActive);

    @PutMapping(value = APP_ROOT + "/game/migrate", produces = MediaType.APPLICATION_JSON_VALUE)
    void migrateGameData(@RequestBody Integer sourceGameId,@RequestBody Integer targetGameId);

    @GetMapping(value = APP_ROOT + "/game/recent", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameDto> getRecentlyUpdatedGames(Pageable pageable);

    //Validation

    @GetMapping(value = APP_ROOT + "/game/name/{name}/available", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean checkNameAvailability(@PathVariable String name);

    @GetMapping(value = APP_ROOT + "/game/{gameId}/age/{userAge}/valid", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean validateAgeRequirements(@PathVariable Integer gameId,@PathVariable Integer userAge);

    //Recommandations

    @GetMapping(value = APP_ROOT + "/game/trending/{limit}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<GameDto> getTrendingGames(@PathVariable int limit);

    //Gestion des assets

    @PutMapping(value = APP_ROOT + "/game/{gameId}/thumbnail", produces = MediaType.APPLICATION_JSON_VALUE)
    GameDto updateGameThumbnail(@PathVariable Integer gameId,@RequestBody String thumbnailUrl);

    @PutMapping(value = APP_ROOT + "/game/{gameId}/description", produces = MediaType.APPLICATION_JSON_VALUE)
    GameDto updateGameDescription(@PathVariable Integer gameId,@RequestBody String description);
}
