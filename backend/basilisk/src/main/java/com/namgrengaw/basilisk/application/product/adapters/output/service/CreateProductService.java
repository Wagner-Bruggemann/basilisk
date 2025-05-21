package com.namgrengaw.basilisk.application.product.adapters.output.service;

import com.namgrengaw.basilisk.application.infrastructure.mappers.EntityMapper;
import com.namgrengaw.basilisk.application.product.adapters.output.entity.ProductEntity;
import com.namgrengaw.basilisk.application.product.adapters.output.repository.ProductRepository;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.input.CreateProductOutputGateway;
import org.springframework.stereotype.Service;

@Service
public class CreateProductService implements CreateProductOutputGateway {

    private final ProductRepository productRepository;
    private final EntityMapper<Product, ProductEntity> productMapper;

    public CreateProductService(ProductRepository productRepository, EntityMapper<Product, ProductEntity> productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Product create(Product product) {
        final ProductEntity entity = productMapper.toEntity(product);
        final ProductEntity persistedEntity = this.productRepository.save(entity);
        return productMapper.toDomain(persistedEntity);
    }
}
