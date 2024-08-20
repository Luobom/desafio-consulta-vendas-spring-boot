package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Seller;

public record SellerBasicDTO_(
        Long id,
        String name
) {

    public SellerBasicDTO_(Seller entity) {
        this(entity.getId(), entity.getName());
    }

}
