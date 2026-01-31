package com.DB.SecurityProject.service;

import com.DB.SecurityProject.dto.RegisterRequest;
import com.DB.SecurityProject.dto.UserDto;
import com.DB.SecurityProject.model.Role;
import com.DB.SecurityProject.model.User;
import com.DB.SecurityProject.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;

        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDto> findAll(){
        return userRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }
    private UserDto toDto(User user){
        return new UserDto(
                user.getUsername()
        );
    }


    public void register(RegisterRequest registerRequest) {
    if(userRepository.existsByEmail(registerRequest.getEmail())){
        throw new  ResponseStatusException(
                HttpStatus.CONFLICT,
                "User with that email already exists"
        );
    }
    User user = new User();
    user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
    user.setEmail(registerRequest.getEmail());
    user.setUsername(registerRequest.getUsername());
    user.setRole(Role.USER);
    userRepository.save(user);
    }
}
