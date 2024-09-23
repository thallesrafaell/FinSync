package dev.thallesrafaell.FinSync.repositories;

import dev.thallesrafaell.FinSync.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    UserDetails findByUsername(String username);

    Optional<User> findByEmail(@NotBlank @Email String email);
}
