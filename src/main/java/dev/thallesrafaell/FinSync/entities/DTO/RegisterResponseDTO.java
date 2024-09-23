package dev.thallesrafaell.FinSync.entities.DTO;

import dev.thallesrafaell.FinSync.entities.User;

import java.util.UUID;

public record RegisterResponseDTO(
        UUID id,
        String name,
        String username,
        String email,
        String message
) {
    public RegisterResponseDTO(User user){
        this(user.getId(), user.getName(), user.getUsername(), user.getEmail(), "User registered successfully");
    }
}
