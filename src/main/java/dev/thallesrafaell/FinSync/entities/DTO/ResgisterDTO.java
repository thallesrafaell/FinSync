package dev.thallesrafaell.FinSync.entities.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ResgisterDTO(
         @NotBlank String name,
         @NotBlank String username,
         @NotBlank @Email String email,
         @Pattern(
                 regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$",
                 message = "A senha deve ter no mínimo 8 caracteres, conter pelo menos uma letra maiúscula, um número e um caractere especial."
         )
         String password) {
}