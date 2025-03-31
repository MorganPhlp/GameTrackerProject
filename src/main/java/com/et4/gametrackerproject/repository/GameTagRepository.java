package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.model.Game;
import com.et4.gametrackerproject.model.GameTag;
import com.et4.gametrackerproject.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GameTagRepository extends JpaRepository<GameTag,Integer> {

    // Pagination des tags pour un jeu
    @Query("SELECT gt FROM GameTag gt WHERE gt.game.id = :gameId")
    Page<GameTag> findByGame(Integer gameId, Pageable pageable);

    // Pagination des jeux pour un tag
    @Query("SELECT gt FROM GameTag gt WHERE gt.tag.id = :tagId")
    Page<GameTag> findByTag(Tag tag, Pageable pageable);

    // Recherches de base
    List<GameTag> findByGame(Game game);

    List<GameTag> findByTag(Tag tag);

    @Query("SELECT gt FROM GameTag gt WHERE gt.tag.id = :tagId")
    Optional<GameTag> findByGameAndTag(Integer game, Integer tagId);

    // Compter le nombre de tags pour un jeu
    Long countByGame(Game game);

    // Compter le nombre de jeux pour un tag
    Long countByTag(Tag tag);

    // Trouver les jeux avec un ensemble de tags spécifiques
    @Query("SELECT gt.game FROM GameTag gt WHERE gt.tag IN :tags GROUP BY gt.game HAVING COUNT(DISTINCT gt.tag) = :tagCount")
    List<Game> findGamesWithAllTags(@Param("tags") List<Tag> tags, @Param("tagCount") Long tagCount);

    // Trouver les tags les plus populaires
    @Query("SELECT gt.tag, COUNT(gt) as tagCount FROM GameTag gt GROUP BY gt.tag ORDER BY tagCount DESC")
    List<Object[]> findMostPopularTags(Pageable pageable);

    // Trouver des jeux par nom de tag
    @Query("SELECT gt.game FROM GameTag gt WHERE gt.tag.name = :tagName")
    List<Game> findGamesByTagName(@Param("tagName") String tagName);

}
