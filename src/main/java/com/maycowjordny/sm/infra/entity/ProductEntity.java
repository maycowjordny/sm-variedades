package com.maycowjordny.sm.infra.entity;

import com.maycowjordny.sm.domain.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Builder
@Table(name = "PRODUCT")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    private UUID id;
    private String name;
    private Integer session;
    private BigDecimal price;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public static ProductEntity create(Product product){
        return  ProductEntity.builder()
                .id(product.getId())
                .price(product.getPrice())
                .name(product.getName())
                .session(product.getSession())
                .build();
    }
}
