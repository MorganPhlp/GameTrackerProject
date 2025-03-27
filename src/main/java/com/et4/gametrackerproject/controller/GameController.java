package com.et4.gametrackerproject.controller;

import com.et4.gametrackerproject.controller.api.GameApi;
import com.et4.gametrackerproject.dto.GameDto;
import com.et4.gametrackerproject.enums.DifficultyLevel;
import com.et4.gametrackerproject.enums.GameCategory;
import com.et4.gametrackerproject.model.Tag;
import com.et4.gametrackerproject.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class GameController implements GameApi {

    @Autowired
    private GameService gameService;

    GameController (GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public GameDto createGame(GameDto gameDto) {
        return gameService.createGame(gameDto);
    }

    @Override
    public GameDto updateGame(Integer id, GameDto gameDto) {
        return gameService.updateGame(id, gameDto);
    }

    @Override
    public void deleteGame(Integer id) {
        gameService.deleteGame(id);
    }

    @Override
    public GameDto getGameById(Integer id) {
        return gameService.getGameById(id);
    }

    @Override
    public Page<GameDto> getAllGames(Pageable pageable) {
        return gameService.getAllGames(pageable);
    }

    @Override
    public Page<GameDto> searchGames(String query, Pageable pageable) {
        return gameService.searchGames(query, pageable);
    }

    @Override
    public Page<GameDto> filterByCategory(GameCategory category, Pageable pageable) {
        return gameService.filterByCategory(category, pageable);
    }

    @Override
    public Page<GameDto> filterByDifficulty(DifficultyLevel difficulty, Pageable pageable) {
        return gameService.filterByDifficulty(difficulty, pageable);
    }

    @Override
    public Page<GameDto> filterByAgeRange(Integer minAge, Integer maxAge, Pageable pageable) {
        return gameService.filterByAgeRange(minAge, maxAge, pageable);
    }

    @Override
    public Page<GameDto> filterByTags(Set<String> tags, Pageable pageable) {
        return gameService.filterByTags(tags, pageable);
    }
}
