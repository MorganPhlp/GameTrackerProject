package com.et4.gametrackerproject.controller;

import com.et4.gametrackerproject.services.GameCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameCommentController {

    @Autowired
    private GameCommentService gameCommentService;
}
