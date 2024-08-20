package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;

import java.time.LocalDate;

public record SaleDTO_(
        Long id,
        Integer visited,
        Integer deals,
        Double amount,
        LocalDate date,
        SellerBasicDTO_ seller
) {
    public SaleDTO_(Sale entity) {
        this(entity.getId(), entity.getVisited(), entity.getDeals(), entity.getAmount(), entity.getDate(), new SellerBasicDTO_(entity.getSeller()));
    }
}