package com.namgrengaw.basilisk.application.product.adapters.output.service;

import com.namgrengaw.basilisk.application.infrastructure.mappers.EntityMapper;
import com.namgrengaw.basilisk.application.product.adapters.mappers.ProductOutputMapper;
import com.namgrengaw.basilisk.application.product.adapters.output.entity.ProductEntity;
import com.namgrengaw.basilisk.application.product.adapters.output.repository.ProductRepository;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.output.GetProductsByNameOutputGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductsByNameSevice implements GetProductsByNameOutputGateway {

    private final ProductRepository productRepository;
    private final EntityMapper<Product, ProductEntity> productMapper;

    public GetProductsByNameSevice(ProductRepository productRepository, EntityMapper<Product, ProductEntity> productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> getProductsByName(String name) {
        final List<ProductEntity> foundEntities = productRepository.findAllByName(name).orElseThrow();
        return foundEntities.stream()
                .map(productMapper::toDomain)
                .toList();
    }
}
