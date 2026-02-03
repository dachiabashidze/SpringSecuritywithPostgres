package com.DB.SecurityProject.controller;

import com.DB.SecurityProject.dto.ProfileDto;
import com.DB.SecurityProject.dto.RegisterRequest;
import com.DB.SecurityProject.dto.UserDto;
import com.DB.SecurityProject.model.CustomUserDetails;
import com.DB.SecurityProject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    UserService userService;
    UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/members")
    public List<UserDto> findAll(){
        return userService.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest){
        userService.register(registerRequest);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileDto> getCurrentUserProfile(@AuthenticationPrincipal CustomUserDetails userDetails){
        ProfileDto profile = userService.getCurrentUserProfile(userDetails.getId());
        return ResponseEntity.ok(profile);
    }

}
