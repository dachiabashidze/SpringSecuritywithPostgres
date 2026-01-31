package com.DB.SecurityProject.config;

import com.DB.SecurityProject.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password4j.BcryptPassword4jPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // implement SessionRegistry, to find out which users are loggin in currently

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
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/",
                                "/register",
                                "/login",
                                "/register.html",
                                "/login.html",
                                "/css/**",
                                "/js/**",
                                "/images/**").permitAll()
                        .anyRequest().authenticated()

                )
//                .httpBasic(basic->{})
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(form -> form
                        .loginPage("/login.html")      // your page
                        .loginProcessingUrl("/login") // Spring intercepts this
                        .defaultSuccessUrl("/members.html", true)
                        .failureUrl("/login.html?error")
                        .permitAll()
                )
                .logout(logout->logout
                        .logoutUrl("/logout")
                        .permitAll()
                )
                .userDetailsService(customUserDetailsService);


        return http.build();
    }
    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
