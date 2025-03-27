package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.GameRecommendationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

public interface GameRecommendationApi {

    //Opérations de base

    @PostMapping(value = APP_ROOT + "/recommendation/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    GameRecommendationDto createRecommendation(@RequestBody Integer senderId,@RequestBody Integer receiverId,@RequestBody Integer gameId,@RequestBody String message);

    @PutMapping(value = APP_ROOT + "/recommendation/{recommendationId}/update", produces = MediaType.APPLICATION_JSON_VALUE)
    GameRecommendationDto updateRecommendationMessage(@PathVariable Integer recommendationId,@RequestBody String newMessage);

    @DeleteMapping(value = APP_ROOT + "/recommendation/{recommendationId}")
    void deleteRecommendation(@PathVariable Integer recommendationId);

    //Récupération

    @GetMapping(value = APP_ROOT + "/recommendation/{recommendationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    GameRecommendationDto getRecommendationById(@PathVariable Integer recommendationId);

    @GetMapping(value = APP_ROOT + "/recommendation/sender/{senderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameRecommendationDto> getRecommendationsBySender(@PathVariable Integer senderId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/recommendation/receiver/{receiverId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameRecommendationDto> getRecommendationsByReceiver(@PathVariable Integer receiverId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/recommendation/users/{user1Id}/{user2Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameRecommendationDto> getRecommendationsBetweenUsers(@PathVariable Integer user1Id,@PathVariable Integer user2Id, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/recommendation/game/{gameId}/count", produces = MediaType.APPLICATION_JSON_VALUE)
    Long countRecommendationsForGame(@PathVariable Integer gameId);

    // Modération

    @GetMapping(value = APP_ROOT + "/recommendation/all", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameRecommendationDto> getAllRecommendations(Pageable pageable);

    @GetMapping(value = APP_ROOT + "/recommendation/search", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameRecommendationDto> searchRecommendations(@RequestBody String searchQuery, Pageable pageable);

    void removeAllRecommendationsBetweenUsers(Integer user1Id, Integer user2Id, Pageable pageable);

    @DeleteMapping(value = APP_ROOT + "/recommendation/remove/game/{gameId}")
    void removeAllRecommendationsForGame(@PathVariable Integer gameId);
}
