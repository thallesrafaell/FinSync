package dev.thallesrafaell.FinSync.repositories;

import dev.thallesrafaell.FinSync.entities.Bills;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillsRepository extends JpaRepository<Bills, Long> {
}
