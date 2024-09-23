package dev.thallesrafaell.FinSync.repositories;

import dev.thallesrafaell.FinSync.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransanctionRepository extends JpaRepository<Transaction, Long> {
}
