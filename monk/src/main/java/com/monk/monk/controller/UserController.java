package com.monk.monk.controller;

import com.monk.monk.dto.BankResponse;
import com.monk.monk.dto.UserRequest;
import com.monk.monk.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // Constructor-based Dependency Injection
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public BankResponse createAccount(@Valid @RequestBody UserRequest userRequest) {
        return userService.createAccount(userRequest);
    }
}
