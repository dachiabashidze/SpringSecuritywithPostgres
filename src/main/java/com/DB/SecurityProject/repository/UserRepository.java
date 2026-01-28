package com.DB.SecurityProject.repository;

import com.DB.SecurityProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
