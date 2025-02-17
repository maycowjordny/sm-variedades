package com.maycowjordny.sm.application.useCase.product;

import com.maycowjordny.sm.infra.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteProductUseCase {

    private final ProductRepository productRepository;

    public void execute(UUID id){
        productRepository.delete(id);
    }

}
