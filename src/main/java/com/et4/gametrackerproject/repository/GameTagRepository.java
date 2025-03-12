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

    // Recherches de base
    List<GameTag> findByGame(Game game);

    List<GameTag> findByTag(Tag tag);

    Optional<GameTag> findByGameAndTag(Game game, Tag tag);

    // Vérifier si un tag existe déjà pour un jeu
    boolean existsByGameAndTag(Game game, Tag tag);

    // Supprimer un tag spécifique d'un jeu
    void deleteByGameAndTag(Game game, Tag tag);

    // Compter le nombre de tags pour un jeu
    Long countByGame(Game game);

    // Compter le nombre de jeux pour un tag
    Long countByTag(Tag tag);

    // Pagination des tags pour un jeu
    Page<GameTag> findByGame(Game game, Pageable pageable);

    // Pagination des jeux pour un tag
    Page<GameTag> findByTag(Tag tag, Pageable pageable);

    // Trouver les jeux avec un ensemble de tags spécifiques
    @Query("SELECT gt.game FROM GameTag gt WHERE gt.tag IN :tags GROUP BY gt.game HAVING COUNT(DISTINCT gt.tag) = :tagCount")
    List<Game> findGamesWithAllTags(@Param("tags") List<Tag> tags, @Param("tagCount") Long tagCount);

    // Trouver les tags les plus populaires
    @Query("SELECT gt.tag, COUNT(gt) as tagCount FROM GameTag gt GROUP BY gt.tag ORDER BY tagCount DESC")
    List<Object[]> findMostPopularTags(Pageable pageable);

    // Trouver des jeux par nom de tag
    @Query("SELECT gt.game FROM GameTag gt WHERE gt.tag.name = :tagName")
    List<Game> findGamesByTagName(@Param("tagName") String tagName);

    // Trouver les tags par nom partiel
    @Query("SELECT DISTINCT gt.tag FROM GameTag gt WHERE LOWER(gt.tag.name) LIKE LOWER(CONCAT('%', :partialName, '%'))")
    List<Tag> findTagsByPartialName(@Param("partialName") String partialName);

    // Trouver les jeux qui ont tous les tags sauf un tag spécifique
    @Query("SELECT gt.game FROM GameTag gt " +
            "WHERE gt.tag IN :includeTags " +
            "AND gt.game NOT IN (SELECT gt2.game FROM GameTag gt2 WHERE gt2.tag = :excludeTag) " +
            "GROUP BY gt.game " +
            "HAVING COUNT(DISTINCT gt.tag) = :includeTagsCount")
    List<Game> findGamesWithTagsButNotSpecificTag(
            @Param("includeTags") List<Tag> includeTags,
            @Param("excludeTag") Tag excludeTag,
            @Param("includeTagsCount") Long includeTagsCount);

    // Trouver les tags les plus utilisés par catégorie de jeu
    @Query("SELECT g.category, gt.tag, COUNT(gt) as tagUseCount " +
            "FROM GameTag gt " +
            "JOIN gt.game g " +
            "GROUP BY g.category, gt.tag " +
            "ORDER BY g.category, tagUseCount DESC")
    List<Object[]> findMostUsedTagsByCategory();
}
