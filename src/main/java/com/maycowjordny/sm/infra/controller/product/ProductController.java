package com.maycowjordny.sm.infra.controller.product;

import com.maycowjordny.sm.application.useCase.product.CreateProductUseCase;
import com.maycowjordny.sm.application.useCase.product.DeleteProductUseCase;
import com.maycowjordny.sm.application.useCase.product.ListProductsUseCase;
import com.maycowjordny.sm.application.useCase.product.UpdateProductUseCase;
import com.maycowjordny.sm.domain.model.Product;
import com.maycowjordny.sm.infra.dto.ListProductsDtoResponse;
import com.maycowjordny.sm.infra.dto.ProductDtoRequest;
import com.maycowjordny.sm.infra.mapper.ProductMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final CreateProductUseCase createProductUseCase;
    private final ListProductsUseCase listProductsUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;

    @PostMapping("/add")
    public ResponseEntity<UUID> create(@Valid @RequestBody ProductDtoRequest productDtoRequest){
        final var result =  createProductUseCase.execute(ProductMapper.convertDtoRequestToDomain(productDtoRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Product> update(@PathVariable UUID id, @Valid @RequestBody ProductDtoRequest productDtoRequest){
         var productDomain =  ProductMapper.convertDtoRequestToDomain(productDtoRequest);
         productDomain.setId(id);
         var result = updateProductUseCase.execute(productDomain);

        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<Map<String, List<ListProductsDtoResponse>>> list() {
        final var result = listProductsUseCase.execute();

        List<ListProductsDtoResponse> products = result.stream()
                .map(ListProductsDtoResponse::new)
                .collect(Collectors.toList());

        Map<String, List<ListProductsDtoResponse>> response = new HashMap<>();
        response.put("products", products);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        deleteProductUseCase.execute(id);
        return ResponseEntity.ok().body("Produto deletado com sucesso!");
    }
}
