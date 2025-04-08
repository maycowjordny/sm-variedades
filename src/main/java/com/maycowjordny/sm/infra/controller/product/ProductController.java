package com.maycowjordny.sm.infra.controller.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maycowjordny.sm.application.useCase.product.CreateProductUseCase;
import com.maycowjordny.sm.application.useCase.product.DeleteProductUseCase;
import com.maycowjordny.sm.application.useCase.product.FindProductByIdUseCase;
import com.maycowjordny.sm.application.useCase.product.ListProductsUseCase;
import com.maycowjordny.sm.application.useCase.product.UpdateProductUseCase;
import com.maycowjordny.sm.domain.model.Product;
import com.maycowjordny.sm.infra.dto.ListProductsDtoResponse;
import com.maycowjordny.sm.infra.dto.ProductDtoRequest;
import com.maycowjordny.sm.infra.mapper.ProductMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final CreateProductUseCase createProductUseCase;
    private final ListProductsUseCase listProductsUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final FindProductByIdUseCase findProductByIdUseCase;

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


    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable UUID id){
       final var result = findProductByIdUseCase.execute(id);
        return ResponseEntity.ok().body(result);
    }
}
