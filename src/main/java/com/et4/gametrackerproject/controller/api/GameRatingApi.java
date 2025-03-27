package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.GameRatingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

public interface GameRatingApi {

    //Opérations de base

    @PostMapping(value = APP_ROOT + "/rating/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    GameRatingDto submitRating(@RequestBody GameRatingDto ratingDto);

    @PutMapping(value = APP_ROOT + "/rating/{ratingId}/update", produces = MediaType.APPLICATION_JSON_VALUE)
    GameRatingDto updateRating(@PathVariable Integer ratingId,@RequestBody Integer newRating);

    @DeleteMapping(value = APP_ROOT + "/rating/{ratingId}")
    void deleteRating(@PathVariable Integer ratingId);

    //Récupération des évaluations

    @GetMapping(value = APP_ROOT + "/rating/{ratingId}", produces = MediaType.APPLICATION_JSON_VALUE)
    GameRatingDto getRatingById(@PathVariable Integer ratingId);

    @GetMapping(value = APP_ROOT + "/rating/user/{userId}/game/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    GameRatingDto getUserRatingForGame(@PathVariable Integer userId,@PathVariable Integer gameId);

    @GetMapping(value = APP_ROOT + "/rating/game/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameRatingDto> getRatingsForGame(@PathVariable Integer gameId, Pageable pageable);

    //Statistiques

    @GetMapping(value = APP_ROOT + "/rating/game/{gameId}/average", produces = MediaType.APPLICATION_JSON_VALUE)
    Double calculateAverageRatingForGame(@PathVariable Integer gameId);

    @GetMapping(value = APP_ROOT + "/rating/game/{gameId}/count", produces = MediaType.APPLICATION_JSON_VALUE)
    Long countRatingsForGame(@PathVariable Integer gameId);

    //Modération

    @GetMapping(value = APP_ROOT + "/rating/recent", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameRatingDto> getRecentRatings(Pageable pageable);

    //Administration

    @DeleteMapping(value = APP_ROOT + "/rating/admin/game/{gameId}/delete")
    void deleteAllRatingsForGame(@PathVariable Integer gameId);

    @DeleteMapping(value = APP_ROOT + "/rating/admin/user/{userId}/delete")
    void deleteAllRatingsForUser(@PathVariable Integer userId);

    @GetMapping(value = APP_ROOT + "/rating/search", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameRatingDto> searchRatings(@RequestBody String searchQuery, Pageable pageable);

    //Analyse

    @GetMapping(value = APP_ROOT + "/rating/top/{limit}", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<Integer, Long> getTopRatedGames(@PathVariable int limit);
}
