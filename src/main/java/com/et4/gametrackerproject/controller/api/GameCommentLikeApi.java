package com.et4.gametrackerproject.controller.api;


import com.et4.gametrackerproject.dto.GameCommentLikeDto;
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
    GameCommentLikeDto toggleLike(@PathVariable Integer userId,@PathVariable Integer commentId);

    @DeleteMapping(value = APP_ROOT + "/game-comment-likes/remove/{likeId}")
    void removeLike(@PathVariable Integer likeId);

    @GetMapping(value = APP_ROOT + "/game-comment-likes/{likeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    GameCommentLikeDto getLikeById(@PathVariable Integer likeId);

    // Récupération

    @GetMapping(value = APP_ROOT + "/game-comment-likes/comment/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameCommentLikeDto> getLikesForComment(@PathVariable Integer commentId, Pageable pageable);


    Page<GameCommentLikeDto> getLikesByUser(Integer userId, Pageable pageable);
    boolean hasUserLikedComment(Integer userId, Integer commentId);

    //Statistiques
    Long getLikeCountForComment(Integer commentId);
    Map<Integer, Long> getLikeCountsForComments(List<Integer> commentIds);
    Map<Integer, Long> getMostLikedComments(int limit);

    //Gestion batch
    int batchRemoveLikesForComment(Integer commentId);
    int batchRemoveLikesByUser(Integer userId);
    Map<Integer, Integer> batchToggleLikes(Integer userId, List<Integer> commentIds);

    //Modération
    Page<GameCommentLikeDto> getRecentLikes(Pageable pageable);

    //Administration
    Page<GameCommentLikeDto> getAllLikes(Pageable pageable);

    //Relations
    Set<Integer> getLikedCommentIdsForUser(Integer userId);
}
