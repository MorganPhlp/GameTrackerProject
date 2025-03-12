package com.et4.gametrackerproject.services.impl;

import com.et4.gametrackerproject.dto.GameCommentLikeDto;
import com.et4.gametrackerproject.services.GameCommentLikeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class GameCommentLikeServiceImpl implements GameCommentLikeService {
    @Override
    public GameCommentLikeDto toggleLike(Integer userId, Integer commentId) {
        return null;
    }

    @Override
    public void removeLike(Integer likeId) {

    }

    @Override
    public GameCommentLikeDto getLikeById(Integer likeId) {
        return null;
    }

    @Override
    public Page<GameCommentLikeDto> getLikesForComment(Integer commentId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<GameCommentLikeDto> getLikesByUser(Integer userId, Pageable pageable) {
        return null;
    }

    @Override
    public boolean hasUserLikedComment(Integer userId, Integer commentId) {
        return false;
    }

    @Override
    public Long getLikeCountForComment(Integer commentId) {
        return 0L;
    }

    @Override
    public Map<Integer, Long> getLikeCountsForComments(List<Integer> commentIds) {
        return Map.of();
    }

    @Override
    public Map<Integer, Long> getMostLikedComments(int limit) {
        return Map.of();
    }

    @Override
    public int batchRemoveLikesForComment(Integer commentId) {
        return 0;
    }

    @Override
    public int batchRemoveLikesByUser(Integer userId) {
        return 0;
    }

    @Override
    public Map<Integer, Integer> batchToggleLikes(Integer userId, List<Integer> commentIds) {
        return Map.of();
    }

    @Override
    public Page<GameCommentLikeDto> getRecentLikes(Pageable pageable) {
        return null;
    }

    @Override
    public Page<GameCommentLikeDto> getAllLikes(Pageable pageable) {
        return null;
    }

    @Override
    public Set<Integer> getLikedCommentIdsForUser(Integer userId) {
        return Set.of();
    }
}
