package com.challenge.users.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.users.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findOneByEmail(String email);
}
