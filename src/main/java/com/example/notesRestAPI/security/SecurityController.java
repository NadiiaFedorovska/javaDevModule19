package com.example.notesRestAPI.security;

import com.example.notesRestAPI.users.register.RegisterUserRequest;
import com.example.notesRestAPI.users.register.RegisterUserResponse;
import com.example.notesRestAPI.users.register.RegisterUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SecurityController {
    private final RegisterUserService registerUserService;

    @PostMapping("/note/user/register")
    public RegisterUserResponse register(@RequestBody RegisterUserRequest request) {
        return registerUserService.register(request);
    }
}
