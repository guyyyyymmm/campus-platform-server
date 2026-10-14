package com.campus.campus_platform_server.controller;

import com.campus.campus_platform_server.entity.UserAccount;
import com.campus.campus_platform_server.service.UserAccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class UserAccountController {

    private final UserAccountService userAccountService;

    public UserAccountController(
            UserAccountService userAccountService
    ) {
        this.userAccountService = userAccountService;
    }

    @PostMapping("/register")
    public UserAccount register(
            @RequestBody UserAccount userAccount
    ) {
        return userAccountService.register(userAccount);
    }

    @PostMapping("/login")
    public UserAccount login(
            @RequestBody UserAccount userAccount
    ) {
        return userAccountService.login(
                userAccount.getUsername(),
                userAccount.getPassword()
        );
    }
}