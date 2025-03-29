package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.FriendshipDto;
import com.et4.gametrackerproject.dto.UserDto;
import com.et4.gametrackerproject.enums.FriendshipStatus;
import com.et4.gametrackerproject.model.User;
import org.springframework.http.MediaType;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

public interface FriendshipApi {

    //Opérations de base
    @PostMapping(value = APP_ROOT + "/friendships/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    FriendshipDto createFriendship(@RequestBody FriendshipDto friendshipDto);

    @PutMapping(value = APP_ROOT + "/friendships/update/{friendshipId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    FriendshipDto updateFriendshipStatus(@PathVariable Integer friendshipId, @RequestBody FriendshipStatus newStatus);

    @DeleteMapping(value = APP_ROOT + "/friendships/delete/{friendshipId}")
    void deleteFriendship(@PathVariable Integer friendshipId);

    //Récupération

    @GetMapping(value = APP_ROOT + "/friendships/{friendshipId}", produces = MediaType.APPLICATION_JSON_VALUE)
    FriendshipDto getFriendshipById(@PathVariable Integer friendshipId);

    @GetMapping(value = APP_ROOT + "/friendships/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<FriendshipDto> getAllFriendshipsForUser(@PathVariable Integer userId);

    @GetMapping(value = APP_ROOT + "/friendships/user/{user1Id}/user/{user2Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    FriendshipDto getFriendshipBetweenUsers(@PathVariable Integer user1Id,@PathVariable Integer user2Id);

    @PostMapping(value = APP_ROOT + "/friendships/send/{senderId}/to/{receiverId}", produces = MediaType.APPLICATION_JSON_VALUE)
    FriendshipDto sendFriendRequest(@PathVariable Integer senderId,@PathVariable Integer receiverId);

    @PutMapping(value = APP_ROOT + "/friendships/accept/{friendshipId}", produces = MediaType.APPLICATION_JSON_VALUE)
    FriendshipDto acceptFriendRequest(@PathVariable Integer friendshipId);

    @PutMapping(value = APP_ROOT + "/friendships/reject/{friendshipId}", produces = MediaType.APPLICATION_JSON_VALUE)
    FriendshipDto rejectFriendRequest(@PathVariable Integer friendshipId);

    @PutMapping(value = APP_ROOT + "/friendships/cancel/{userId}/friend/{friendId}", produces = MediaType.APPLICATION_JSON_VALUE)
    FriendshipDto cancelFriendship(@PathVariable Integer userId,@PathVariable Integer friendId);

    //Listes relationnelles

    @GetMapping(value = APP_ROOT + "/friendships/friends/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserDto> getFriendsList(@PathVariable Integer userId);

    @GetMapping(value = APP_ROOT + "/friendships/mutual/{user1Id}/user/{user2Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserDto> getMutualFriends(@PathVariable Integer user1Id,@PathVariable Integer user2Id);

    @GetMapping(value = APP_ROOT + "/friendships/pending/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserDto> getPendingRequests(@PathVariable Integer userId);

    // Vérifications

    @GetMapping(value = APP_ROOT + "/friendships/exists/{user1Id}/user/{user2Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean friendshipExists(@PathVariable Integer user1Id,@PathVariable Integer user2Id);

    @GetMapping(value = APP_ROOT + "/friendships/pending/{user1Id}/user/{user2Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean hasPendingRequestBetween(@PathVariable Integer user1Id,@PathVariable Integer user2Id);

    @GetMapping(value = APP_ROOT + "/friendships/status/{user1Id}/user/{user2Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    FriendshipStatus getRelationshipStatus(@PathVariable Integer user1Id,@PathVariable Integer user2Id);

    // Statistiques

    @GetMapping(value = APP_ROOT + "/friendships/count/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    int getFriendCount(@PathVariable Integer userId);

    //Administration

    @GetMapping(value = APP_ROOT + "/friendships/admin/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<FriendshipDto> getAllFriendships();

    @GetMapping(value = APP_ROOT + "/friendships/admin/search/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<FriendshipDto> searchFriendshipsByUser(@PathVariable String username);

    @DeleteMapping(value = APP_ROOT + "/friendships/admin/delete/{userId}")
    void removeAllFriendshipsForUser(@PathVariable Integer userId);

    @GetMapping(value = APP_ROOT + "/friendships/suggest/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserDto> suggestFriends(Integer userId);

    @GetMapping(value = APP_ROOT + "/friendships/status/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<FriendshipDto> getFriendshipsForUserByStatus(User user, FriendshipStatus status);

}
