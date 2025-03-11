package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.model.GameComment;
import com.et4.gametrackerproject.model.GameCommentLike;
import com.et4.gametrackerproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GameCommentLikeRepository extends JpaRepository<GameCommentLike,Integer> {

    // Vérifier si un utilisateur a aimé un commentaire spécifique
    Optional<GameCommentLike> findByUserAndComment(User user, GameComment comment);

    // Vérifier l'existence d'un like pour un utilisateur et un commentaire
    boolean existsByUserAndComment(User user, GameComment comment);

    // Récupérer tous les likes pour un commentaire
    List<GameCommentLike> findByComment(GameComment comment);

    // Récupérer tous les likes d'un utilisateur
    List<GameCommentLike> findByUser(User user);

    // Compter le nombre de likes pour un commentaire
    @Query("SELECT COUNT(gcl) FROM GameCommentLike gcl WHERE gcl.comment = :comment")
    Long countByComment(@Param("comment") GameComment comment);

    // Compter le nombre total de likes donnés par un utilisateur
    @Query("SELECT COUNT(gcl) FROM GameCommentLike gcl WHERE gcl.user = :user")
    Long countByUser(@Param("user") User user);

    // Supprimer un like pour un utilisateur et un commentaire spécifiques
    void deleteByUserAndComment(User user, GameComment comment);

    // Récupérer les utilisateurs qui ont aimé un commentaire spécifique
    @Query("SELECT gcl.user FROM GameCommentLike gcl WHERE gcl.comment = :comment")
    List<User> findUsersByComment(@Param("comment") GameComment comment);

    // Récupérer les commentaires aimés par un utilisateur
    @Query("SELECT gcl.comment FROM GameCommentLike gcl WHERE gcl.user = :user")
    List<GameComment> findCommentsByUser(@Param("user") User user);

    // Récupérer les commentaires les plus aimés
    @Query("SELECT gcl.comment, COUNT(gcl) as likeCount FROM GameCommentLike gcl GROUP BY gcl.comment ORDER BY likeCount DESC")
    List<Object[]> findMostLikedComments();
}
