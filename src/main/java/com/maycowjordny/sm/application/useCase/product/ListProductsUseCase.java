package com.maycowjordny.sm.application.useCase.product;

import com.maycowjordny.sm.domain.model.Product;
import com.maycowjordny.sm.infra.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListProductsUseCase {

    private final ProductRepository productRepository;

    public List<Product> execute(){
        return productRepository.list();
    }
}
