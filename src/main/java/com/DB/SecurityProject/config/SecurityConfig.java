package com.DB.SecurityProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

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
                        .defaultSuccessUrl("/homepage")
                        .permitAll()
                )
                .logout(logout->logout
                        .logoutUrl("/logout")
                        .permitAll()
                );

        return http.build();
    }
}
