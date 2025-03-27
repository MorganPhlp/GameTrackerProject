package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.GameDto;
import com.et4.gametrackerproject.enums.DifficultyLevel;
import com.et4.gametrackerproject.enums.GameCategory;
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

    @GetMapping(value = APP_ROOT + "/game/category", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameDto> filterByCategory(@RequestBody GameCategory category, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/game/difficulty", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameDto> filterByDifficulty(@RequestBody DifficultyLevel difficulty, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/game/age", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameDto> filterByAgeRange(@RequestBody Integer minAge,@RequestBody Integer maxAge, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/game/tags", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameDto> filterByTags(@RequestBody Set<String> tags, Pageable pageable);
}
