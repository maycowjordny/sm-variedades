package com.maycowjordny.sm.infra.mapper;

import com.maycowjordny.sm.domain.model.Product;
import com.maycowjordny.sm.infra.dto.ProductDtoRequest;
import com.maycowjordny.sm.infra.entity.ProductEntity;

public class ProductMapper {

    public static Product convertDtoRequestToDomain(ProductDtoRequest productDtoRequest){
        return Product.create(
                null,
                productDtoRequest.getName(),
                productDtoRequest.getPrice(),
                productDtoRequest.getSession(),
                null,
                null
                );
    }

    public  static ProductEntity toEntity(Product product){
        return ProductEntity.create(product);
    }

    public  static  Product toDomain(ProductEntity productEntity){
        return Product.create(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getPrice(),
                productEntity.getSession(),
                productEntity.getCreatedAt(),
                productEntity.getUpdatedAt()
        );
    }
}
