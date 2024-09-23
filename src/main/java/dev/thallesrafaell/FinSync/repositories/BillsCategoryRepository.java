package dev.thallesrafaell.FinSync.repositories;

import dev.thallesrafaell.FinSync.entities.BillsCategory;
import dev.thallesrafaell.FinSync.entities.DTO.BillsCategoryResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BillsCategoryRepository extends JpaRepository<BillsCategory, Long> {
    boolean existsByName(String dados);


    @Query(value = "SELECT * FROM tb_billscategories WHERE unaccent(lower(name)) = unaccent(lower(:categoryName))", nativeQuery = true)
    Optional<BillsCategory> findByNameIgnoreCaseAndUnaccent(@Param("categoryName") String categoryName);


}
