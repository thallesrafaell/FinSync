package dev.thallesrafaell.FinSync.entities.DTO;

import jakarta.validation.constraints.NotBlank;

public record BillsCategoryUpdateDTO(
        @NotBlank  String oldName,
        @NotBlank String newName) {
}
