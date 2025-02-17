package com.maycowjordny.sm.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Getter
@Setter
public class Product {

    private UUID id;
    private String name;
    private BigDecimal price;
    private Integer session;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public static Product create(
            UUID id,
            String name,
            BigDecimal price,
            Integer session,
            Timestamp createdAt,
            Timestamp updatedAt
    ){
            return Product.builder().
                    id((id == null) ? UUID.randomUUID() : id).
                    name(name).
                    price(price).
                    session(session).
                    createdAt(createdAt).
                    updatedAt(updatedAt).build();
    }
}
