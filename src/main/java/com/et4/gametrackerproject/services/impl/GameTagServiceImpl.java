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
    public GameTagDto addTagToGame(Game game, Tag tag) {
        if (game == null || tag == null) {
            throw new IllegalArgumentException("L'ID du jeu et du tag ne peuvent être null");
        }

        // Vérifier si l'association existe déjà (optionnel)
        Optional<GameTag> existing = gameTagRepository.findByGameAndTag(game, tag);
        if (existing.isPresent()) {
            log.info("Association déjà existante entre le jeu {} et le tag {}", game.getName(), tag.getName());
            return GameTagDto.fromEntity(existing.get());
        }

        GameTag gameTag = GameTag.builder()
                .game(game)
                .tag(tag)
                .build();

        GameTag savedAssociation = gameTagRepository.save(gameTag);
        log.info("Tag {} ajouté au jeu {} avec l'association ID {}", tag.getName(), game.getName(), savedAssociation.getId());
        return GameTagDto.fromEntity(savedAssociation);
    }

    @Override
    public Set<GameTagDto> addMultipleTagsToGame(Game game, Set<Tag> tags) {
        if (game == null || tags == null || tags.isEmpty()) {
            throw new IllegalArgumentException("L'ID du jeu et la liste des tags ne peuvent être null ou vide");
        }
        Set<GameTagDto> result = new HashSet<>();
        for (Tag tag : tags) {
            result.add(addTagToGame(game, tag));
        }
        return result;
    }

    @Override
    public int removeMultipleTagsFromGame(Game game, Set<Tag> tags) {
        if (game == null || tags == null || tags.isEmpty()) {
            throw new IllegalArgumentException("L'ID du jeu et la liste des tags ne peuvent être null ou vide");
        }
        int count = 0;
        for (Tag tag : tags) {
            // Suppression individuelle si l'association existe
            Optional<GameTag> association = gameTagRepository.findByGameAndTag(game, tag);
            if (association.isPresent()) {
                gameTagRepository.delete(association.get());
                count++;
                log.info("Association entre le jeu {} et le tag {} supprimée", game.getName(), tag.getName());
            }
        }
        return count;
    }

    @Override
    public void removeTagFromGame(Game game, Tag tag) {
        if (game == null || tag == null) {
            throw new IllegalArgumentException("L'ID du jeu et du tag ne peuvent être null");
        }
        Optional<GameTag> association = gameTagRepository.findByGameAndTag(game, tag);
        if (association.isPresent()) {
            gameTagRepository.delete(association.get());
            log.info("Association entre le jeu {} et le tag {} supprimée", game.getName(), tag.getName());
        } else {
            log.warn("Aucune association trouvée pour le jeu {} et le tag {}", game.getName(), tag.getName());
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
    public Page<GameTagDto> getTagsForGame(Game game, Pageable pageable) {
        if (game == null) {
            throw new IllegalArgumentException("L'ID du jeu ne peut être null");
        }

        Page<GameTagDto> page = gameTagRepository.findByGame(game, pageable)
                .map(GameTagDto::fromEntity);
        log.info("Récupération des tags pour le jeu {} - page {}", game.getName(), pageable.getPageNumber());
        return page;
    }

    @Override
    public Page<GameTagDto> getGamesForTag(Tag tag, Pageable pageable) {
        if (tag == null) {
            throw new IllegalArgumentException("L'ID du tag ne peut être null");
        }

        Page<GameTagDto> page = gameTagRepository.findByTag(tag, pageable)
                .map(GameTagDto::fromEntity);
        log.info("Récupération des associations pour le tag {} - page {}", tag.getName(), pageable.getPageNumber());
        return page;
    }
}
