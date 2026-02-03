package com.DB.SecurityProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/login")
    public String loginPage(){
        return "forward:/login.html";
    }

    @GetMapping("/register")
    public String registerPage(){
        return "forward:/register.html";
    }
    @GetMapping("/members")
    public String membersPage() {
        return "forward:/members.html";
    }
    @GetMapping("/profile")
    public String profilePage() {
        return "forward:/profile.html";
    }

}
