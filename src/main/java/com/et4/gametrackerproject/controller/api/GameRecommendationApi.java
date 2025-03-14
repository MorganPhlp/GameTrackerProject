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

    //Interactions

    @PutMapping(value = APP_ROOT + "/recommendation/{recommendationId}/view", produces = MediaType.APPLICATION_JSON_VALUE)
    GameRecommendationDto markAsViewed(@PathVariable Integer recommendationId);

    @PutMapping(value = APP_ROOT + "/recommendation/{recommendationId}/accept", produces = MediaType.APPLICATION_JSON_VALUE)
    GameRecommendationDto acceptRecommendation(@PathVariable Integer recommendationId);

    //Statistiques

    @GetMapping(value = APP_ROOT + "/recommendation/mostRecommended/{limit}", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<Integer, Long> getMostRecommendedGames(@PathVariable int limit);

    @GetMapping(value = APP_ROOT + "/recommendation/topRecommenders/{limit}", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<Integer, Long> getTopRecommenders(@PathVariable int limit);

    @GetMapping(value = APP_ROOT + "/recommendation/game/{gameId}/count", produces = MediaType.APPLICATION_JSON_VALUE)
    Long countRecommendationsForGame(@PathVariable Integer gameId);

    //Vérifications

    @GetMapping(value = APP_ROOT + "/recommendation/canRecommend/{senderId}/{receiverId}/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean canRecommendGame(@PathVariable Integer senderId,@PathVariable Integer receiverId,@PathVariable Integer gameId);

    @GetMapping(value = APP_ROOT + "/recommendation/hasRecommended/{senderId}/{receiverId}/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean hasRecommendedGameToUser(@PathVariable Integer senderId,@PathVariable Integer receiverId,@PathVariable Integer gameId);

    @GetMapping(value = APP_ROOT + "/recommendation/isActive/{recommendationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean isRecommendationActive(@PathVariable Integer recommendationId);

    // Modération

    @GetMapping(value = APP_ROOT + "/recommendation/all", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameRecommendationDto> getAllRecommendations(Pageable pageable);

    @GetMapping(value = APP_ROOT + "/recommendation/search", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameRecommendationDto> searchRecommendations(@RequestBody String searchQuery, Pageable pageable);

    //Intégration

    @PutMapping(value = APP_ROOT + "/recommendation/{recommendationId}/notify")
    void notifyReceiver(@PathVariable Integer recommendationId);

    @PutMapping(value = APP_ROOT + "/recommendation/sync/{user1Id}/{user2Id}")
    void syncWithFriendshipService(@PathVariable Integer user1Id,@PathVariable Integer user2Id);

    //Gestion des relations

    @DeleteMapping(value = APP_ROOT + "/recommendation/remove/{user1Id}/{user2Id}")
    void removeAllRecommendationsBetweenUsers(@PathVariable Integer user1Id,@PathVariable Integer user2Id);

    @DeleteMapping(value = APP_ROOT + "/recommendation/remove/game/{gameId}")
    void removeAllRecommendationsForGame(@PathVariable Integer gameId);

    @PutMapping(value = APP_ROOT + "/recommendation/refresh")
    void refreshRecommendationStatuses();
}
