package com.maycowjordny.sm.application.useCase.product;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.maycowjordny.sm.domain.model.Product;
import com.maycowjordny.sm.infra.repository.product.ProductRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class FindProductByIdUseCase {

    private final ProductRepository productRepository;

      public Product execute(UUID id){
        return productRepository.findById(id);
    }
    
}
