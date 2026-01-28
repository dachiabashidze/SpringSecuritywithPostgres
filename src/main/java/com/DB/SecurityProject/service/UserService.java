package com.DB.SecurityProject.service;

import com.DB.SecurityProject.dto.UserDto;
import com.DB.SecurityProject.model.User;
import com.DB.SecurityProject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    UserService(UserRepository userRepository){
        this.userRepository = userRepository;

    }

    // binding User to UserDto
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

    


}
