package com.DB.SecurityProject.config;

import com.DB.SecurityProject.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    CustomUserDetailsService customUserDetailsService;
    SecurityConfig(CustomUserDetailsService customUserDetailsService){
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/login", "/register").permitAll()
                        .anyRequest().authenticated()

                )
                .httpBasic(basic->{})
                .formLogin(form-> form
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/members",true)
                        .permitAll()
                )
                .logout(logout->logout
                        .logoutUrl("/logout")
                        .permitAll()
                )
                .userDetailsService(customUserDetailsService);


        return http.build();
    }
}
