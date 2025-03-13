package com.et4.gametrackerproject.services.impl;

import com.et4.gametrackerproject.dto.FriendshipDto;
import com.et4.gametrackerproject.dto.UserDto;
import com.et4.gametrackerproject.enums.FriendshipStatus;
import com.et4.gametrackerproject.services.FriendshipService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FriendshipServiceImpl implements FriendshipService {
    @Override
    public FriendshipDto createFriendship(FriendshipDto friendshipDto) {
        return null;
    }

    @Override
    public FriendshipDto updateFriendshipStatus(Integer friendshipId, FriendshipStatus newStatus) {
        return null;
    }

    @Override
    public void deleteFriendship(Integer friendshipId) {

    }

    @Override
    public FriendshipDto getFriendshipById(Integer friendshipId) {
        return null;
    }

    @Override
    public List<FriendshipDto> getAllFriendshipsForUser(Integer userId) {
        return List.of();
    }

    @Override
    public List<FriendshipDto> getFriendshipsByStatus(Integer userId, FriendshipStatus status) {
        return List.of();
    }

    @Override
    public FriendshipDto getFriendshipBetweenUsers(Integer user1Id, Integer user2Id) {
        return null;
    }

    @Override
    public FriendshipDto sendFriendRequest(Integer senderId, Integer receiverId) {
        return null;
    }

    @Override
    public FriendshipDto acceptFriendRequest(Integer friendshipId) {
        return null;
    }

    @Override
    public FriendshipDto rejectFriendRequest(Integer friendshipId) {
        return null;
    }

    @Override
    public FriendshipDto cancelFriendship(Integer userId, Integer friendId) {
        return null;
    }

    @Override
    public List<UserDto> getFriendsList(Integer userId) {
        return List.of();
    }

    @Override
    public List<UserDto> getMutualFriends(Integer user1Id, Integer user2Id) {
        return List.of();
    }

    @Override
    public List<UserDto> getPendingRequests(Integer userId) {
        return List.of();
    }

    @Override
    public boolean friendshipExists(Integer user1Id, Integer user2Id) {
        return false;
    }

    @Override
    public boolean hasPendingRequestBetween(Integer user1Id, Integer user2Id) {
        return false;
    }

    @Override
    public FriendshipStatus getRelationshipStatus(Integer user1Id, Integer user2Id) {
        return null;
    }

    @Override
    public int getFriendCount(Integer userId) {
        return 0;
    }

    @Override
    public List<FriendshipDto> getAllFriendships() {
        return List.of();
    }

    @Override
    public List<FriendshipDto> searchFriendshipsByUser(String username) {
        return List.of();
    }

    @Override
    public void removeAllFriendshipsForUser(Integer userId) {

    }

    @Override
    public void resolveDuplicateFriendships(Integer userId) {

    }

    @Override
    public void mergeDuplicateFriendships(Integer user1Id, Integer user2Id) {

    }

    @Override
    public void notifyFriendshipUpdate(Integer friendshipId) {

    }
}
