package com.DB.SecurityProject.controller;

import com.DB.SecurityProject.dto.RegisterRequest;
import com.DB.SecurityProject.dto.UserDto;
import com.DB.SecurityProject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class UserController {
    UserService userService;
    UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping
    public List<UserDto> findAll(){
        return userService.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest){
        userService.register(registerRequest);
        return ResponseEntity.ok("User registered successfully");
    }

}
