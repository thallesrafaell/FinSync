package dev.thallesrafaell.FinSync.entities.DTO;

import dev.thallesrafaell.FinSync.entities.Bills;

import java.time.LocalDateTime;
import java.util.UUID;

public record BillResponseDTO(
               Long id,
               String name,
               Double value,
               LocalDateTime dueDaten,
               Boolean paid,
               Long categoryId,
               UUID walletId
) {
    public BillResponseDTO(Bills bills) {
        this(bills.getId(), bills.getName(), bills.getValue(), bills.getDueDate(),bills.getPaid(), bills.getCategory().getId(), bills.getWallet().getId());
    }
}
