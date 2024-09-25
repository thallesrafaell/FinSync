package dev.thallesrafaell.FinSync.entities.DTO;

import dev.thallesrafaell.FinSync.entities.BillsCategory;
import dev.thallesrafaell.FinSync.entities.Wallet;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record BillsDTO (
        @NotBlank String name,
        @NotNull Double value,
        @NotBlank LocalDateTime dueDate,
        @NotNull BillsCategory category,
        Wallet wallet
){
}
