package com.et4.gametrackerproject.controller.api;


import com.et4.gametrackerproject.dto.GameCommentLikeDto;
import com.et4.gametrackerproject.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

public interface GameCommentLikeApi {

    //Opérations de base

    @PostMapping(value = APP_ROOT + "/game-comment-likes/toggle/{userId}/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    GameCommentLikeDto addCommentLike(@PathVariable Integer userId,@PathVariable Integer commentId);

    @DeleteMapping(value = APP_ROOT + "/game-comment-likes/remove/{likeId}")
    void removeLike(@PathVariable Integer likeId);

    @GetMapping(value = APP_ROOT + "/game-comment-likes/{likeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    GameCommentLikeDto getLikeById(@PathVariable Integer likeId);

    // Récupération

    @GetMapping(value = APP_ROOT + "/game-comment-likes/comment/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameCommentLikeDto> getLikesForComment(@PathVariable Integer commentId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/game-comment-likes/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameCommentLikeDto> getLikesByUser(@PathVariable Integer userId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/game-comment-likes/count/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Long getLikeCountForComment(@PathVariable Integer commentId);

    @GetMapping(value = APP_ROOT + "/game-comment-likes/most-liked/{limit}", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<Integer, Long> getMostLikedComments(@PathVariable int limit);

    //Modération

    @GetMapping(value = APP_ROOT + "/game-comment-likes/recent", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameCommentLikeDto> getRecentLikes(Pageable pageable);

    //Administration

    @GetMapping(value = APP_ROOT + "/game-comment-likes/admin/all", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameCommentLikeDto> getAllLikes(Pageable pageable);

    @GetMapping(value = APP_ROOT + "/game-comment-likes/comment/{commentId}/users", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserDto> getUsersWhoLikedComment(@PathVariable Integer commentId);

    @GetMapping(value = APP_ROOT + "/game-comment-likes/user/{userId}/count", produces = MediaType.APPLICATION_JSON_VALUE)
    Long countLikesByUser(@PathVariable Integer userId);

}
