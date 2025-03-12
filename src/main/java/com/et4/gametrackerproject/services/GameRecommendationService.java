package com.et4.gametrackerproject.services;

import com.et4.gametrackerproject.dto.GameRecommendationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface GameRecommendationService {

    //Opérations de base
    GameRecommendationDto createRecommendation(Integer senderId, Integer receiverId, Integer gameId, String message);

    GameRecommendationDto updateRecommendationMessage(Integer recommendationId, String newMessage);

    void deleteRecommendation(Integer recommendationId);

    //Récupération
    GameRecommendationDto getRecommendationById(Integer recommendationId);

    Page<GameRecommendationDto> getRecommendationsBySender(Integer senderId, Pageable pageable);

    Page<GameRecommendationDto> getRecommendationsByReceiver(Integer receiverId, Pageable pageable);

    Page<GameRecommendationDto> getRecommendationsBetweenUsers(Integer user1Id, Integer user2Id, Pageable pageable);

    //Interactions
    GameRecommendationDto markAsViewed(Integer recommendationId);

    GameRecommendationDto acceptRecommendation(Integer recommendationId);

    //Statistiques
    Map<Integer, Long> getMostRecommendedGames(int limit);

    Map<Integer, Long> getTopRecommenders(int limit);

    Long countRecommendationsForGame(Integer gameId);

    //Vérifications
    boolean canRecommendGame(Integer senderId, Integer receiverId, Integer gameId);

    boolean hasRecommendedGameToUser(Integer senderId, Integer receiverId, Integer gameId);

    boolean isRecommendationActive(Integer recommendationId);

    // Modération
    Page<GameRecommendationDto> getAllRecommendations(Pageable pageable);

    Page<GameRecommendationDto> searchRecommendations(String searchQuery, Pageable pageable);

    //Intégration
    void notifyReceiver(Integer recommendationId);

    void syncWithFriendshipService(Integer user1Id, Integer user2Id);

    //Gestion des relations
    void removeAllRecommendationsBetweenUsers(Integer user1Id, Integer user2Id);

    void removeAllRecommendationsForGame(Integer gameId);

    void refreshRecommendationStatuses();

}