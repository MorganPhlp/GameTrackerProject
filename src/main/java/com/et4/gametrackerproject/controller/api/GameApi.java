package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.GameDto;
import com.et4.gametrackerproject.enums.DifficultyLevel;
import com.et4.gametrackerproject.enums.GameCategory;
import com.et4.gametrackerproject.model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    @GetMapping(value = APP_ROOT + "/game/search/{query}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameDto> searchGames(@PathVariable String query, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/game/{category}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameDto> filterByCategory( @PathVariable GameCategory category, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/game/{difficulty}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameDto> filterByDifficulty( @PathVariable DifficultyLevel difficulty, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/game/{minAge}-{maxAge} ans", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameDto> filterByAgeRange( @PathVariable Integer minAge,@PathVariable Integer maxAge, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/game/tags", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameDto> filterByTags(@RequestBody Set<String> tags, Pageable pageable);


    // Recherche d'un jeu par son URL
    @GetMapping(value = APP_ROOT + "/game/url/{url}", produces = MediaType.APPLICATION_JSON_VALUE)
    Optional<Game> getGameByUrl(@PathVariable String url);

    // Recherche des jeux par nom exact
    @GetMapping(value = APP_ROOT + "/game/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Game> getGamesByName(@PathVariable String name);

    // Recherche des jeux actifs avec pagination
    @GetMapping(value = APP_ROOT + "/game/active", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<Game> getGamesByIsActive(Boolean isActive, Pageable pageable);

    // Recherche des jeux par catégorie et niveau de difficulté
    @GetMapping(value = APP_ROOT + "/game/category/difficulty", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Game> getGamesByCategoryAndDifficulty(GameCategory category, DifficultyLevel difficultyLevel);

    // Recherche des jeux ayant une note supérieure ou égale à minRating
    @GetMapping(value = APP_ROOT + "/game/rating", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Game> getHighlyRatedGames(Double minRating);

    // Recherche des jeux les plus populaires (triés par playCount décroissant)
    @GetMapping(value = APP_ROOT + "/game/popular", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Game> getMostPopularGames(Pageable pageable);

    // Recherche des jeux accessibles pour un âge donné (minAge <= age)
    @GetMapping(value = APP_ROOT + "/game/<={age} ans", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Game> getGamesByMinAgeLessThanEqual(@PathVariable Integer age);

    // Recherche combinée avec plusieurs filtres
    @GetMapping(value = APP_ROOT + "/game/filters", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<Game> getGamesWithFilters(String name, GameCategory category, DifficultyLevel difficulty,
                                   Double minRating, Integer minAge, Pageable pageable);

    // Recherche des jeux les plus récents
    @GetMapping(value = APP_ROOT + "/game/newest", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Game> getNewestGames(Pageable pageable);

    // Recherche des jeux populaires par catégorie
    @GetMapping(value = APP_ROOT + "/game/popular/category", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Game> getMostPopularGamesByCategory(GameCategory category, Pageable pageable);

}
