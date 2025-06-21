package com.project.whiteboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.project.whiteboard.model.User;
import com.project.whiteboard.service.UserService;
import org.springframework.security.core.Authentication;


@RestController
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private UserService service;

    @PostMapping("signup")
    public User register(@RequestBody User user) {
        return service.saveUser(user);
    }
    
    @GetMapping("/me")
    public String getCurrentUsername(Authentication authentication) {
        return authentication.getName(); 
    }

}
