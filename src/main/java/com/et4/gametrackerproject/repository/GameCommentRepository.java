package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.model.Game;
import com.et4.gametrackerproject.model.GameComment;
import com.et4.gametrackerproject.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface GameCommentRepository extends JpaRepository<GameComment,Integer> {

    // Récupérer tous les commentaires pour un jeu spécifique
    List<GameComment> findByGame(Game game);

    // Récupérer tous les commentaires d'un utilisateur
    List<GameComment> findByUser(User user);

    // Récupérer tous les commentaires de premier niveau (sans parent) pour un jeu
    List<GameComment> findByGameAndParentCommentIsNull(Game game);

    // Récupérer tous les commentaires en réponse à un commentaire parent
    List<GameComment> findByParentComment(GameComment parentComment);

    // Récupérer tous les commentaires d'un utilisateur pour un jeu spécifique
    List<GameComment> findByUserAndGame(User user, Game game);

    // Pagination pour les commentaires d'un jeu
    Page<GameComment> findByGame(Game game, Pageable pageable);

    // Pagination pour les commentaires de premier niveau d'un jeu
    Page<GameComment> findByGameAndParentCommentIsNull(Game game, Pageable pageable);

    // Compter le nombre de commentaires pour un jeu
    @Query("SELECT COUNT(gc) FROM GameComment gc WHERE gc.game = :game")
    Long countByGame(@Param("game") Game game);

    // Compter le nombre de commentaires par un utilisateur
    @Query("SELECT COUNT(gc) FROM GameComment gc WHERE gc.user = :user")
    Long countByUser(@Param("user") User user);

    // Compter le nombre de réponses à un commentaire parent
    @Query("SELECT COUNT(gc) FROM GameComment gc WHERE gc.parentComment = :parentComment")
    Long countReplies(@Param("parentComment") GameComment parentComment);

    // Trouver les commentaires les plus récents pour un jeu
    List<GameComment> findByGameOrderByCreationDateDesc(Game game);

    // Trouver les commentaires les plus populaires (plus de likes)
    @Query("SELECT gc, COUNT(gcl) AS likeCount FROM GameComment gc LEFT JOIN gc.likes gcl " +
            "WHERE gc.game = :game GROUP BY gc ORDER BY likeCount DESC")
    List<Object[]> findMostLikedCommentsByGame(@Param("game") Game game);

    // Trouver les commentaires avec le plus de réponses
    @Query("SELECT gc, COUNT(gr) AS replyCount FROM GameComment gc LEFT JOIN gc.replies gr WHERE gc.game = :game GROUP BY gc ORDER BY replyCount DESC")
    List<Object[]> findMostDiscussedCommentsByGame(@Param("game") Game game);

    // Trouver les utilisateurs les plus actifs (avec le plus de commentaires) sur tous les jeux
    @Query("SELECT gc.user, COUNT(gc) as commentCount FROM GameComment gc GROUP BY gc.user ORDER BY commentCount DESC")
    List<Object[]> findMostActiveCommenters();

    // Trouver les commentaires d'un utilisateur qui ont reçu des réponses
    @Query("SELECT DISTINCT gc FROM GameComment gc WHERE gc.user = :user AND SIZE(gc.replies) > 0")
    List<GameComment> findCommentsWithRepliesByUser(@Param("user") User user);

    // Analyse des tendances: jeux avec l'augmentation la plus rapide de commentaires
    @Query(value =
            "SELECT g.id, g.name, COUNT(gc.id) as comment_count FROM game g " +
                    "JOIN gamecomment gc ON gc.game_id = g.id " +
                    "WHERE gc.creation_date >= :since " +
                    "GROUP BY g.id, g.name " +
                    "ORDER BY comment_count DESC",
            nativeQuery = true)
    List<Object[]> findTrendingGamesByComments(@Param("since") Instant since);
}
