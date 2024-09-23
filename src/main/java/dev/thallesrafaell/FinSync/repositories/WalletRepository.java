package dev.thallesrafaell.FinSync.repositories;

import dev.thallesrafaell.FinSync.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
}
