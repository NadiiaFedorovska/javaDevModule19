package com.example.notesRestAPI.users.register;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String username;
    private String password;
}
