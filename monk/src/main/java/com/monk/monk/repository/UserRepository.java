package com.monk.monk.repository;

import com.monk.monk.entity.User; // Import your User entity
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email); // Change to match field name in User
    Boolean existsByAccountNumber(String accountNumber);

    Optional<User> findByAccountNumber(String accountNumber);
}
