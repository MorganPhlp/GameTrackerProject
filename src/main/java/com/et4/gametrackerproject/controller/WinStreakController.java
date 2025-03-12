package com.et4.gametrackerproject.controller;

import com.et4.gametrackerproject.services.WinStreakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WinStreakController {

    @Autowired
    private WinStreakService winStreakService;
}
