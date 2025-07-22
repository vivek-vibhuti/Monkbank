package com.monk.monk.controller;

import com.monk.monk.dto.BankResponse;
import com.monk.monk.dto.CreditDebitRequest;
import com.monk.monk.dto.EnquireyRequest;
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
    @GetMapping("/balanceEnq")
    public BankResponse balanceenq(@RequestBody EnquireyRequest request){
        return userService.balanceEnquiry(request);
    }
    @GetMapping("/nameEnq")
     public String nameEnq(@RequestBody EnquireyRequest request){
        return userService.nameEnquiry(request);
     }
     @PostMapping("/credit")
     public BankResponse creditAccount(@RequestBody CreditDebitRequest request){
        return userService.creditAccount(request);

     }

}
