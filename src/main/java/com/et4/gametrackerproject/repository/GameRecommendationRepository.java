package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.model.Game;
import com.et4.gametrackerproject.model.GameRecommendation;
import com.et4.gametrackerproject.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GameRecommendationRepository extends JpaRepository<GameRecommendation,Integer>{

    // Rechercher les recommandations envoyées par un utilisateur
    List<GameRecommendation> findBySender(User sender);

    // Rechercher les recommandations envoyées par un utilisateur avec pagination
    Page<GameRecommendation> findBySender(User sender, Pageable pageable);

    // Rechercher les recommandations reçues par un utilisateur
    List<GameRecommendation> findByReceiver(User receiver);

    // Rechercher les recommandations reçues par un utilisateur avec pagination
    Page<GameRecommendation> findByReceiver(User receiver, Pageable pageable);

    // Rechercher les recommandations pour un jeu spécifique
    List<GameRecommendation> findByGame(Game game);

    // Rechercher les recommandations pour un jeu spécifique avec pagination
    Page<GameRecommendation> findByGame(Game game, Pageable pageable);

    // Trouver une recommandation spécifique entre deux utilisateurs pour un jeu
    Optional<GameRecommendation> findBySenderAndReceiverAndGame(User sender, User receiver, Game game);

    // Compter le nombre de recommandations reçues par un utilisateur
    Long countByReceiver(User receiver);

    // Compter le nombre de recommandations envoyées par un utilisateur
    Long countBySender(User sender);

    // Trouver les recommandations d'un utilisateur à un autre
    List<GameRecommendation> findBySenderAndReceiver(User sender, User receiver);

    // Trouver les recommandations pour un utilisateur qui n'ont pas encore été consultées
    @Query(value =
            "SELECT gr.* FROM gamerecommendation gr " +
                    "LEFT JOIN gamerating grt ON grt.user_id = gr.receiver_id AND grt.game_id = gr.game_id " +
                    "LEFT JOIN gameprogress gp ON gp.user_id = gr.receiver_id AND gp.game_id = gr.game_id " +
                    "WHERE gr.receiver_id = :receiverId " +
                    "AND grt.id IS NULL AND gp.id IS NULL",
            nativeQuery = true)
    List<GameRecommendation> findPendingRecommendations(@Param("receiverId") Integer receiverId);
}
