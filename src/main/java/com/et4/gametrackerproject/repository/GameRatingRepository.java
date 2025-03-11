package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.model.Game;
import com.et4.gametrackerproject.model.GameRating;
import com.et4.gametrackerproject.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GameRatingRepository extends JpaRepository<GameRating,Integer> {

    // Trouver la note d'un utilisateur pour un jeu spécifique
    Optional<GameRating> findByUserAndGame(User user, Game game);

    // Récupérer toutes les notes pour un jeu spécifique
    List<GameRating> findByGame(Game game);

    // Récupérer toutes les notes d'un utilisateur spécifique
    List<GameRating> findByUser(User user);

    // Récupérer toutes les notes d'un utilisateur avec pagination
    Page<GameRating> findByUser(User user, Pageable pageable);

    // Récupérer toutes les notes pour un jeu avec pagination
    Page<GameRating> findByGame(Game game, Pageable pageable);

    // Compter le nombre de notes pour un jeu
    Long countByGame(Game game);

    // Calculer la note moyenne pour un jeu
    @Query("SELECT AVG(gr.rating) FROM GameRating gr WHERE gr.game = :game")
    Double calculateAverageRatingForGame(@Param("game") Game game);

    // Trouver les jeux les mieux notés (note moyenne > valeur indiquée)
    @Query("SELECT gr.game, AVG(gr.rating) as avgRating FROM GameRating gr " +
            "GROUP BY gr.game HAVING AVG(gr.rating) >= :minRating " +
            "ORDER BY avgRating DESC")
    List<Object[]> findHighlyRatedGames(@Param("minRating") Double minRating);

    // Trouver les jeux les mieux notés avec un nombre minimum de notes
    @Query("SELECT gr.game, AVG(gr.rating) as avgRating, COUNT(gr.id) as ratingCount FROM GameRating gr " +
            "GROUP BY gr.game HAVING COUNT(gr.id) >= :minRatings " +
            "ORDER BY avgRating DESC")
    List<Object[]> findTopRatedGamesWithMinRatings(@Param("minRatings") Long minRatings);

    // Trouver la distribution des notes pour un jeu (combien de 1, 2, 3, 4, 5 étoiles)
    @Query("SELECT gr.rating, COUNT(gr.id) FROM GameRating gr WHERE gr.game = :game GROUP BY gr.rating ORDER BY gr.rating")
    List<Object[]> findRatingDistributionForGame(@Param("game") Game game);

    // Récupérer les jeux les mieux notés par genre
    @Query(value =
            "SELECT g.category, g.id, g.name, AVG(gr.rating) as avg_rating, COUNT(gr.id) as rating_count " +
                    "FROM game g " +
                    "JOIN gamerating gr ON gr.game_id = g.id " +
                    "GROUP BY g.category, g.id, g.name, g.id, g.name " +
                    "HAVING COUNT(gr.id) >= :minRatings " +
                    "ORDER BY g.category, avg_rating DESC",
            nativeQuery = true)
    List<Object[]> findTopRatedGamesByGenre(@Param("minRatings") Integer minRatings);

    // Mettre à jour la note d'un utilisateur pour un jeu
    @Modifying
    @Query("UPDATE GameRating gr SET gr.rating = :rating WHERE gr.user = :user AND gr.game = :game")
    void updateRating(@Param("user") User user, @Param("game") Game game, @Param("rating") Integer rating);

    // Supprimer toutes les notes pour un jeu spécifique
    @Modifying
    @Query("DELETE FROM GameRating gr WHERE gr.game = :game")
    void deleteAllRatingsForGame(@Param("game") Game game);
}
