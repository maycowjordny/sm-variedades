package com.maycowjordny.sm.infra.repository.product;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.maycowjordny.sm.domain.model.Product;

@Repository
public interface ProductRepository {
    void save(Product product);
    Product update(Product product);
    List<Product> list();
    Product findById(UUID id);
    void delete(UUID id);
}