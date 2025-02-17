package com.maycowjordny.sm.infra.repository.product;

import com.maycowjordny.sm.domain.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository {
    void save(Product product);
    Product update(Product product);
    List<Product> list();
    void delete(UUID id);
}