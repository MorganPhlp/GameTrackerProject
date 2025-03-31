package com.et4.gametrackerproject.services.impl;

import com.et4.gametrackerproject.dto.GameTagDto;
import com.et4.gametrackerproject.exception.EntityNotFoundException;
import com.et4.gametrackerproject.exception.ErrorCodes;
import com.et4.gametrackerproject.model.Game;
import com.et4.gametrackerproject.model.GameTag;
import com.et4.gametrackerproject.model.Tag;
import com.et4.gametrackerproject.repository.GameRepository;
import com.et4.gametrackerproject.repository.GameTagRepository;
import com.et4.gametrackerproject.repository.TagRepository;
import com.et4.gametrackerproject.services.GameTagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class GameTagServiceImpl implements GameTagService {

    private static final Logger log = LoggerFactory.getLogger(GameTagServiceImpl.class);

    private final GameTagRepository gameTagRepository;
    private final TagRepository tagRepository;

    public GameTagServiceImpl(GameTagRepository gameTagRepository,
                              GameRepository gameRepository,
                              TagRepository tagRepository) {
        this.gameTagRepository = gameTagRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public GameTagDto addTagToGame(Integer gameId, Integer tagId) {
        if (gameId == null || tagId == null) {
            throw new IllegalArgumentException("L'ID du jeu et du tag ne peuvent être null");
        }

        // Vérifier si l'association existe déjà (optionnel)
        Optional<GameTag> existing = gameTagRepository.findByGameAndTag(gameId, tagId);
        if (existing.isPresent()) {
            log.info("Association déjà existante entre le jeu {} et le tag {}", gameId, tagId);
            return GameTagDto.fromEntity(existing.get());
        }

        GameTag gameTag = GameTag.builder()
                .game(Game.builder().id(gameId).build())
                .tag(Tag.builder().id(tagId).build())
                .build();

        GameTag savedAssociation = gameTagRepository.save(gameTag);
        log.info("Tag {} ajouté au jeu {} avec l'association ID {}", tagId, gameId, savedAssociation.getId());
        return GameTagDto.fromEntity(savedAssociation);
    }

    @Override
    public Set<GameTagDto> addMultipleTagsToGame(Integer gameId, Set<Tag> tags) {
        if (gameId == null || tags == null || tags.isEmpty()) {
            throw new IllegalArgumentException("L'ID du jeu et la liste des tags ne peuvent être null ou vide");
        }
        Set<GameTagDto> result = new HashSet<>();
        for (Tag tag : tags) {
            result.add(addTagToGame(gameId, tag.getId()));
        }
        return result;
    }

    @Override
    public int removeMultipleTagsFromGame(Integer gameId, Set<Tag> tags) {
        if (gameId == null || tags == null || tags.isEmpty()) {
            throw new IllegalArgumentException("L'ID du jeu et la liste des tags ne peuvent être null ou vide");
        }
        int count = 0;
        for (Tag tag : tags) {
            // Suppression individuelle si l'association existe
            Optional<GameTag> association = gameTagRepository.findByGameAndTag(gameId, tag.getId());
            if (association.isPresent()) {
                gameTagRepository.delete(association.get());
                count++;
                log.info("Association entre le jeu {} et le tag {} supprimée", gameId, tag.getName());
            }
        }
        return count;
    }

    @Override
    public void removeTagFromGame(Integer gameId, Integer tagId) {
        if (gameId == null || tagId == null) {
            throw new IllegalArgumentException("L'ID du jeu et du tag ne peuvent être null");
        }
        Optional<GameTag> association = gameTagRepository.findByGameAndTag(gameId, tagId);
        if (association.isPresent()) {
            gameTagRepository.delete(association.get());
            log.info("Association entre le jeu {} et le tag {} supprimée", gameId, tagId);
        } else {
            log.warn("Aucune association trouvée pour le jeu {} et le tag {}", gameId, tagId);
        }
    }

    @Override
    public GameTagDto updateTagAssociation(Integer associationId, Integer newTagId) {
        if (associationId == null || newTagId == null) {
            throw new IllegalArgumentException("L'ID de l'association et du nouveau tag ne peuvent être null");
        }
        GameTag association = gameTagRepository.findById(associationId)
                .orElseThrow(() -> new EntityNotFoundException("Association non trouvée avec l'ID " + associationId, ErrorCodes.GAME_TAG_NOT_FOUND));

        Tag newTag = tagRepository.findById(newTagId)
                .orElseThrow(() -> new EntityNotFoundException("Tag non trouvé avec l'ID " + newTagId, ErrorCodes.TAG_NOT_FOUND));

        association.setTag(newTag);
        GameTag updatedAssociation = gameTagRepository.save(association);
        log.info("Association {} mise à jour avec le nouveau tag {}", associationId, newTagId);
        return GameTagDto.fromEntity(updatedAssociation);
    }

    @Override
    public Page<GameTagDto> getTagsForGame(Integer gameId, Pageable pageable) {
        if (gameId == null) {
            throw new IllegalArgumentException("L'ID du jeu ne peut être null");
        }

        Page<GameTagDto> page = gameTagRepository.findByGame(gameId, pageable)
                .map(GameTagDto::fromEntity);
        log.info("Récupération des tags pour le jeu {} - page {}", gameId, pageable.getPageNumber());
        return page;
    }

    @Override
    public Page<GameTagDto> getGamesForTag(Integer tagId, Pageable pageable) {
        if (tagId == null) {
            throw new IllegalArgumentException("L'ID du tag ne peut être null");
        }

        Page<GameTagDto> page = gameTagRepository.findByTag(tagId, pageable)
                .map(GameTagDto::fromEntity);
        log.info("Récupération des associations pour le tag {} - page {}", tagId, pageable.getPageNumber());
        return page;
    }

    @Override
    public Long countTagsByGame(Integer gameId) {
        if (gameId == null) {
            throw new IllegalArgumentException("L'ID du jeu ne peut être null");
        }
        return gameTagRepository.countTagsByGame(gameId);
    }

    @Override
    public Long countGamesByTag(Integer tagId) {
        if (tagId == null) {
            throw new IllegalArgumentException("L'ID du tag ne peut être null");
        }
        return gameTagRepository.countGamesByTag(tagId);
    }

    @Override
    public Set<GameTagDto> getMostPopularTags(Pageable pageable) {
        if (pageable == null) {
            throw new IllegalArgumentException("La pagination ne peut être null");
        }
        Set<GameTagDto> popularTags = new HashSet<>();
        gameTagRepository.findMostPopularTags(pageable).forEach(tag -> {
            popularTags.add(GameTagDto.fromEntity((GameTag) tag[0]));
        });
        log.info("Récupération des tags les plus populaires - page {}", pageable.getPageNumber());
        return popularTags;
    }

}
