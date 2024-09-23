package dev.thallesrafaell.FinSync.entities.DTO;

import jakarta.validation.constraints.NotBlank;

public record BillsCategoryDTO(@NotBlank String name) {
}
