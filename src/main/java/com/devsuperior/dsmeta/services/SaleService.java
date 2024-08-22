package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;


import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleBasicDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleService {

	LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

	@Autowired
	private SaleRepository repository;

	@Transactional(readOnly = true)
	public SaleBasicDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleBasicDTO(entity);
	}


	@Transactional(readOnly = true)
	public Page<List<SaleBasicDTO>> getReport(String minDate, String maxDate, String name, Pageable pageable) {
		LocalDate minLocalDate;
		LocalDate maxLocalDate;

		if (maxDate.isEmpty()) maxLocalDate = today;
		else maxLocalDate = LocalDate.parse(maxDate);

		if (minDate.isEmpty()) minLocalDate = maxLocalDate.minusYears(1L);
		else minLocalDate = LocalDate.parse(minDate);

		return repository.getReport(minLocalDate, maxLocalDate, name, pageable);
	}


	@Transactional(readOnly = true)
	public List<SaleSummaryDTO> getSummary(String minDate, String maxDate) {

		LocalDate minLocalDate;
		LocalDate maxLocalDate;

		if (maxDate.isEmpty()) maxLocalDate = today;
		else maxLocalDate = LocalDate.parse(maxDate);

		if (minDate.isEmpty()) minLocalDate = maxLocalDate.minusYears(1L);
		else minLocalDate = LocalDate.parse(minDate);

		return repository.getSummary(minLocalDate, maxLocalDate);
	}

}
