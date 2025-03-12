package com.et4.gametrackerproject.controller;

import com.et4.gametrackerproject.services.GameRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameRecommendationController {

    @Autowired
    private GameRecommendationService gameRecommendationService;
}
