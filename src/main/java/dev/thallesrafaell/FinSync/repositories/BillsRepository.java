package dev.thallesrafaell.FinSync.repositories;

import dev.thallesrafaell.FinSync.entities.Bills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BillsRepository extends JpaRepository<Bills, Long> {

    @Query("SELECT b FROM Bills b WHERE b.wallet.id = :walletId")
    List<Bills> findAllByWalletId(@Param("walletId") UUID uuid);
}
