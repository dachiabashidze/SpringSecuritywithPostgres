package com.DB.SecurityProject.controller;

import com.DB.SecurityProject.dto.UserDto;
import com.DB.SecurityProject.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
