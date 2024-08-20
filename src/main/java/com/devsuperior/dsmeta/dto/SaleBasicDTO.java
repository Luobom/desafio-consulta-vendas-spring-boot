package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;

public record SaleBasicDTO(
			Long id,
			Double amount,
			LocalDate date,
			String sellerName

){
	public SaleBasicDTO(Sale entity) {
		this(entity.getId(), entity.getAmount(), entity.getDate(), entity.getSeller().getName());
	}
}

