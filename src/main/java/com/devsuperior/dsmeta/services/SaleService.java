package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;


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
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate minLocalDate;
		LocalDate maxLocalDate;

		if (maxDate.isEmpty()) maxLocalDate = today;
		else maxLocalDate = LocalDate.parse(maxDate);

		if (minDate.isEmpty()) minLocalDate = maxLocalDate.minusYears(1L);
		else minLocalDate = LocalDate.parse(minDate);

		// debug
		System.out.println("minLocalDate: " + minLocalDate);
		System.out.println("maxLocalDate: " + maxLocalDate);
		System.out.println("name: " + name);

		return repository.getReport(minLocalDate, maxLocalDate, name, pageable);
	}

}
