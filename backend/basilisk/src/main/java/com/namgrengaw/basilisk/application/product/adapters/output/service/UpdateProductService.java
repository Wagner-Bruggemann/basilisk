package com.namgrengaw.basilisk.application.product.adapters.output.service;

import com.namgrengaw.basilisk.application.infrastructure.mappers.EntityMapper;
import com.namgrengaw.basilisk.application.product.adapters.output.entity.ProductEntity;
import com.namgrengaw.basilisk.application.product.adapters.output.repository.ProductRepository;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.output.UpdateProductOutputGateway;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductService implements UpdateProductOutputGateway {

    private final ProductRepository productRepository;
    private final EntityMapper<Product, ProductEntity> mapper;

    public UpdateProductService(ProductRepository productRepository, EntityMapper<Product, ProductEntity> mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public Product update(Product product) {
        ProductEntity entity = mapper.toEntity(product);
        ProductEntity persistedEntity = productRepository.save(entity);
        return mapper.toDomain(persistedEntity);
    }
}
