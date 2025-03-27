package com.et4.gametrackerproject.services.impl;

import com.et4.gametrackerproject.dto.GameDto;
import com.et4.gametrackerproject.enums.DifficultyLevel;
import com.et4.gametrackerproject.enums.GameCategory;
import com.et4.gametrackerproject.exception.EntityNotFoundException;
import com.et4.gametrackerproject.exception.ErrorCodes;
import com.et4.gametrackerproject.model.Game;
import com.et4.gametrackerproject.repository.GameRepository;
import com.et4.gametrackerproject.services.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {
    private static final Logger log = LoggerFactory.getLogger(GameServiceImpl.class);
    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public GameDto createGame(GameDto gameDto) {
        if(gameDto == null) {
            throw new IllegalArgumentException("Le GameDto ne peut être null");
        }
        // Conversion du DTO en entité
        Game game = GameDto.toEntity(gameDto);
        // Définition de la date de création si besoin
        game.setCreationDate(java.time.Instant.now());
        game.setLastModifiedDate(java.time.Instant.now());
        Game savedGame = gameRepository.save(game);
        log.info("Jeu créé avec l'ID {}", savedGame.getId());
        // Conversion de l'entité sauvegardée en DTO
        return GameDto.fromEntity(savedGame);
    }

    @Override
    public GameDto updateGame(Integer id, GameDto gameDto) {
        if(id == null || gameDto == null) {
            throw new IllegalArgumentException("L'ID et le GameDto ne peuvent être null");
        }
        // Vérifier que le jeu existe
        Game existingGame = gameRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Jeu non trouvé avec l'ID " + id, ErrorCodes.GAME_NOT_FOUND));
        // Mettre à jour les propriétés souhaitées (exemple simple, adapter selon vos besoins)
        existingGame.setName(gameDto.getName());
        existingGame.setUrl(gameDto.getUrl());
        existingGame.setDescription(gameDto.getDescription());
        existingGame.setCategory(gameDto.getCategory());
        existingGame.setAverageRating(gameDto.getAverageRating());
        existingGame.setPlayCount(gameDto.getPlayCount());
        existingGame.setDifficultyLevel(gameDto.getDifficultyLevel());
        existingGame.setMinAge(gameDto.getMinAge());
        existingGame.setIsActive(gameDto.getIsActive());
        // Mettre à jour la date de dernière modification
        existingGame.setLastModifiedDate(java.time.Instant.now());

        Game updatedGame = gameRepository.save(existingGame);
        log.info("Jeu avec l'ID {} mis à jour", id);
        return GameDto.fromEntity(updatedGame);
    }

    @Override
    public void deleteGame(Integer id) {
        if(id == null) {
            throw new IllegalArgumentException("L'ID du jeu ne peut être null");
        }
        Game existingGame = gameRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Jeu non trouvé avec l'ID " + id, ErrorCodes.GAME_NOT_FOUND));
        gameRepository.delete(existingGame);
        log.info("Jeu avec l'ID {} supprimé", id);
    }

    //=======================GETTER===========================

    @Override
    public GameDto getGameById(Integer id) {
        if(id == null) {
            throw new IllegalArgumentException("L'ID du jeu ne peut être null");
        }
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Jeu non trouvé avec l'ID " + id, ErrorCodes.GAME_NOT_FOUND));
        log.info("Jeu récupéré avec l'ID {}", id);
        return GameDto.fromEntity(game);
    }

    @Override
    public Page<GameDto> getAllGames(Pageable pageable) {
        Page<Game> games = gameRepository.findAll(pageable);
        log.info("Récupération de tous les jeux, page {}", pageable.getPageNumber());
        return games.map(GameDto::fromEntity);
    }

    @Override
    public Page<GameDto> searchGames(String query, Pageable pageable) {
        if(query == null || query.trim().isEmpty()) {
            throw new IllegalArgumentException("La requête de recherche ne peut être vide");
        }
        Page<Game> games = gameRepository.findByNameContainingIgnoreCase(query, pageable);
        log.info("Recherche de jeux avec la requête '{}' sur la page {}", query, pageable.getPageNumber());
        return games.map(GameDto::fromEntity);
    }

    @Override
    public Page<GameDto> filterByCategory(GameCategory category, Pageable pageable) {
        if(category == null) {
            throw new IllegalArgumentException("La catégorie ne peut être null");
        }
        Page<Game> games = gameRepository.findByCategory(category, pageable);
        log.info("Filtrage des jeux par catégorie '{}' sur la page {}", category, pageable.getPageNumber());
        return games.map(GameDto::fromEntity);
    }

    @Override
    public Page<GameDto> filterByDifficulty(DifficultyLevel difficulty, Pageable pageable) {
        if(difficulty == null) {
            throw new IllegalArgumentException("Le niveau de difficulté ne peut être null");
        }
        Page<Game> games = gameRepository.findByDifficultyLevel(difficulty, pageable);
        log.info("Filtrage des jeux par difficulté '{}' sur la page {}", difficulty, pageable.getPageNumber());
        return games.map(GameDto::fromEntity);
    }

    @Override
    public Page<GameDto> filterByAgeRange(Integer minAge, Integer maxAge, Pageable pageable) {
        if(minAge == null || maxAge == null) {
            throw new IllegalArgumentException("Les bornes d'âge ne peuvent être null");
        }
        if(minAge > maxAge) {
            throw new IllegalArgumentException("L'âge minimum ne peut être supérieur à l'âge maximum");
        }
        Page<Game> games = gameRepository.findByMinAgeBetween(minAge, maxAge, pageable);
        log.info("Filtrage des jeux par tranche d'âge entre {} et {} sur la page {}", minAge, maxAge, pageable.getPageNumber());
        return games.map(GameDto::fromEntity);
    }

    @Override
    public Page<GameDto> filterByTags(Set<String> tags, Pageable pageable) {
        if(tags == null || tags.isEmpty()) {
            throw new IllegalArgumentException("La liste de tags ne peut être vide");
        }
        // On suppose ici que le repository dispose d'une méthode pour filtrer par tags via le nom des tags.
        Page<Game> games = gameRepository.findGamesByTags(tags, pageable);
        log.info("Filtrage des jeux par tags {} sur la page {}", tags, pageable.getPageNumber());
        return games.map(GameDto::fromEntity);
    }

    // Recherche d'un jeu par son URL
    @Override
    public Optional<Game> getGameByUrl(String url) {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("L'URL ne peut être null ou vide");
        }
        Optional<Game> gameOpt = gameRepository.findByUrl(url);
        log.info("Recherche de jeu par URL : {}", url);
        return gameOpt;
    }

    // Recherche des jeux par nom exact
    @Override
    public List<Game> getGamesByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Le nom ne peut être null ou vide");
        }
        List<Game> games = gameRepository.findByName(name);
        log.info("Recherche de jeux par nom : {}", name);
        return games;
    }

    // Recherche des jeux actifs avec pagination
    @Override
    public Page<Game> getGamesByIsActive(Boolean isActive, Pageable pageable) {
        if (isActive == null) {
            throw new IllegalArgumentException("Le paramètre isActive ne peut être null");
        }
        Page<Game> games = gameRepository.findByIsActive(isActive, pageable);
        log.info("Recherche de jeux actifs : {} sur la page {}", isActive, pageable.getPageNumber());
        return games;
    }

    // Recherche des jeux par catégorie et niveau de difficulté
    @Override
    public List<Game> getGamesByCategoryAndDifficulty(GameCategory category, DifficultyLevel difficultyLevel) {
        if (category == null || difficultyLevel == null) {
            throw new IllegalArgumentException("La catégorie et le niveau de difficulté ne peuvent être null");
        }
        List<Game> games = gameRepository.findByCategoryAndDifficultyLevel(category, difficultyLevel);
        log.info("Recherche de jeux par catégorie {} et difficulté {}", category, difficultyLevel);
        return games;
    }

    // Recherche des jeux ayant une note supérieure ou égale à minRating
    @Override
    public List<Game> getHighlyRatedGames(Double minRating) {
        if (minRating == null) {
            throw new IllegalArgumentException("La note minimale ne peut être null");
        }
        List<Game> games = gameRepository.findHighlyRatedGames(minRating);
        log.info("Recherche de jeux avec une note supérieure ou égale à {}", minRating);
        return games;
    }

    // Recherche des jeux les plus populaires (triés par playCount décroissant)
    @Override
    public List<Game> getMostPopularGames(Pageable pageable) {
        List<Game> games = gameRepository.findMostPopularGames(pageable);
        log.info("Recherche des jeux les plus populaires sur la page {}", pageable.getPageNumber());
        return games;
    }

    // Recherche des jeux accessibles pour un âge donné (minAge <= age)
    @Override
    public List<Game> getGamesByMinAgeLessThanEqual(Integer age) {
        if (age == null) {
            throw new IllegalArgumentException("L'âge ne peut être null");
        }
        List<Game> games = gameRepository.findByMinAgeLessThan(age);
        log.info("Recherche de jeux accessibles pour un âge inférieur ou égal à {}", age);
        return games;
    }

    // Recherche combinée avec plusieurs filtres
    @Override
    public Page<Game> getGamesWithFilters(String name, GameCategory category, DifficultyLevel difficulty,
                                          Double minRating, Integer minAge, Pageable pageable) {
        Page<Game> games = gameRepository.findGamesWithFilters(name, category, difficulty, minRating, minAge, pageable);
        log.info("Recherche combinée de jeux avec filtres [name: {}, category: {}, difficulty: {}, minRating: {}, minAge: {}] sur la page {}",
                name, category, difficulty, minRating, minAge, pageable.getPageNumber());
        return games;
    }

    // Recherche des jeux les plus récents
    @Override
    public List<Game> getNewestGames(Pageable pageable) {
        List<Game> games = gameRepository.findNewestGames(pageable);
        log.info("Recherche des jeux les plus récents sur la page {}", pageable.getPageNumber());
        return games;
    }

    // Recherche des jeux populaires par catégorie
    @Override
    public List<Game> getMostPopularGamesByCategory(GameCategory category, Pageable pageable) {
        if (category == null) {
            throw new IllegalArgumentException("La catégorie ne peut être null");
        }
        List<Game> games = gameRepository.findMostPopularGamesByCategory(category, pageable);
        log.info("Recherche des jeux populaires dans la catégorie {} sur la page {}", category, pageable.getPageNumber());
        return games;
    }

}
