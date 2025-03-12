package com.et4.gametrackerproject.services.impl;

import com.et4.gametrackerproject.dto.GameRecommendationDto;
import com.et4.gametrackerproject.services.GameRecommendationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GameRecommendationServiceImpl implements GameRecommendationService {
    @Override
    public GameRecommendationDto createRecommendation(Integer senderId, Integer receiverId, Integer gameId, String message) {
        return null;
    }

    @Override
    public GameRecommendationDto updateRecommendationMessage(Integer recommendationId, String newMessage) {
        return null;
    }

    @Override
    public void deleteRecommendation(Integer recommendationId) {

    }

    @Override
    public GameRecommendationDto getRecommendationById(Integer recommendationId) {
        return null;
    }

    @Override
    public Page<GameRecommendationDto> getRecommendationsBySender(Integer senderId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<GameRecommendationDto> getRecommendationsByReceiver(Integer receiverId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<GameRecommendationDto> getRecommendationsBetweenUsers(Integer user1Id, Integer user2Id, Pageable pageable) {
        return null;
    }

    @Override
    public GameRecommendationDto markAsViewed(Integer recommendationId) {
        return null;
    }

    @Override
    public GameRecommendationDto acceptRecommendation(Integer recommendationId) {
        return null;
    }

    @Override
    public Map<Integer, Long> getMostRecommendedGames(int limit) {
        return Map.of();
    }

    @Override
    public Map<Integer, Long> getTopRecommenders(int limit) {
        return Map.of();
    }

    @Override
    public Long countRecommendationsForGame(Integer gameId) {
        return 0L;
    }

    @Override
    public boolean canRecommendGame(Integer senderId, Integer receiverId, Integer gameId) {
        return false;
    }

    @Override
    public boolean hasRecommendedGameToUser(Integer senderId, Integer receiverId, Integer gameId) {
        return false;
    }

    @Override
    public boolean isRecommendationActive(Integer recommendationId) {
        return false;
    }

    @Override
    public Page<GameRecommendationDto> getAllRecommendations(Pageable pageable) {
        return null;
    }

    @Override
    public Page<GameRecommendationDto> searchRecommendations(String searchQuery, Pageable pageable) {
        return null;
    }

    @Override
    public void notifyReceiver(Integer recommendationId) {

    }

    @Override
    public void syncWithFriendshipService(Integer user1Id, Integer user2Id) {

    }

    @Override
    public void removeAllRecommendationsBetweenUsers(Integer user1Id, Integer user2Id) {

    }

    @Override
    public void removeAllRecommendationsForGame(Integer gameId) {

    }

    @Override
    public void refreshRecommendationStatuses() {

    }
}
