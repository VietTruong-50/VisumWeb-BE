package com.hust.visum.repository;

import com.hust.visum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUserName(String username);

    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);
}
