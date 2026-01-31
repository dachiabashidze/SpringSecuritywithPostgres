package com.DB.SecurityProject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
}
