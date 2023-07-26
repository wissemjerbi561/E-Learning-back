package com.insy2s.KeyCloakAuth.repository;

import com.insy2s.KeyCloakAuth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {



    public User  findByUsername(String username);
    public User findUserById(Long id);
}
