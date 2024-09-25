package dev.thallesrafaell.FinSync.entities.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record BillsUpdateDTO(
        @NotNull Long id,
        String name,
        Double value,
        LocalDateTime dueDate,
        Boolean paid,
        Long categoryId
){
}
