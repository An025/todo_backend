package com.codecool.todo_backend.controller;

import com.codecool.todo_backend.model.AppUser;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class UserinfoController {

    @GetMapping("/api/v1/username")
    @ResponseBody
    public String currentUserName(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        AppUser user = (AppUser) authentication.getPrincipal();
        return user.getUsername() + "\n" + user.getRoles();
    }
}

