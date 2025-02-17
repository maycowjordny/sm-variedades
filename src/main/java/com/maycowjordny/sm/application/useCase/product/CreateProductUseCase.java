package com.maycowjordny.sm.application.useCase.product;

import com.maycowjordny.sm.domain.model.Product;
import com.maycowjordny.sm.infra.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateProductUseCase {

    private final ProductRepository productRepository;

    public UUID execute(Product product){
        productRepository.save(product);
        return product.getId();
    }
}
