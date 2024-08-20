package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleBasicDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleBasicDTO(s.id, s.amount, s.date, s.seller.name) FROM Sale s WHERE "
            + "(s.date >= :minLocalDate) AND "
            + "(s.date <= :maxLocalDate) AND "
            + "(UPPER(s.seller.name) LIKE UPPER(CONCAT('%', :name, '%')))")
    Page<List<SaleBasicDTO>> getReport(LocalDate minLocalDate, LocalDate maxLocalDate, String name, Pageable pageable);



}
