package com.maycowjordny.sm.infra.repository.product;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.maycowjordny.sm.domain.model.Product;
import com.maycowjordny.sm.infra.entity.ProductEntity;
import com.maycowjordny.sm.infra.mapper.ProductMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImp implements ProductRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public void save(Product product) {
        entityManager.persist(ProductMapper.toEntity(product));
        entityManager.flush();
    }

    @Override
    @Transactional
    public Product update(Product product) {
        final var productEntity = ProductMapper.toEntity(product);

        final var entity = entityManager.merge(productEntity);

        return ProductMapper.toDomain(entity);
    }

    @Override
    public List<Product> list() {
        String sql = "SELECT p FROM ProductEntity p";

        final var productEntities =  entityManager.createQuery(sql, ProductEntity.class).getResultList();

        return productEntities.stream()
                .map(ProductMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public void delete(UUID id) {
     ProductEntity product = entityManager.find(ProductEntity.class,id);
     entityManager.remove(product);
    }

    @Override
    public Product findById(UUID id) {
        String sql = "SELECT p FROM ProductEntity p WHERE p.id =:id";

          ProductEntity productEntity = entityManager.createQuery(sql, ProductEntity.class)
                .setParameter("id", id)
                .getSingleResult();

        return ProductMapper.toDomain(productEntity);
    }

}
