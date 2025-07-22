package com.monk.monk.repository;

import com.monk.monk.entity.User; // Import your User entity
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email); // Change to match field name in User
    Boolean existsByAccountNumber(String accountNumber);

    User findByAccountNumber(String accountNumber);
}
