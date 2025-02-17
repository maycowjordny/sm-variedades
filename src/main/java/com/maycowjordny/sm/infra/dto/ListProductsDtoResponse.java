package com.maycowjordny.sm.infra.dto;


import com.maycowjordny.sm.domain.model.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ListProductsDtoResponse {
    private UUID id;
    private String name;
    private BigDecimal price;

    public ListProductsDtoResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }
}