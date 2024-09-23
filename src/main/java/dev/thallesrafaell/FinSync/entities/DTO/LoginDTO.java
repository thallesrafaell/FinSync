package dev.thallesrafaell.FinSync.entities.DTO;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank  String username,
        @NotBlank  String password)
{
}
