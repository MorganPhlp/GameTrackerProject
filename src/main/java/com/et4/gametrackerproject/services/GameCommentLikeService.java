package com.et4.gametrackerproject.services;

import com.et4.gametrackerproject.dto.GameCommentLikeDto;
import com.et4.gametrackerproject.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GameCommentLikeService {

    //Opérations de base
    GameCommentLikeDto toggleLike(Integer userId, Integer commentId);
    void removeLike(Integer likeId);
    GameCommentLikeDto getLikeById(Integer likeId);

    // Récupération
    Page<GameCommentLikeDto> getLikesForComment(Integer commentId, Pageable pageable);
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