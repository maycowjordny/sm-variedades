package com.maycowjordny.sm.infra.dto;


import java.math.BigDecimal;
import java.util.UUID;

import com.maycowjordny.sm.domain.model.Product;

import lombok.Data;

@Data
public class ListProductsDtoResponse {
    private UUID id;
    private String name;
    private BigDecimal price;
    private Integer session;

    public ListProductsDtoResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.session = product.getSession();
    }
}