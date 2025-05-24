package com.namgrengaw.basilisk.application.product.adapters.output.service;

import com.namgrengaw.basilisk.application.infrastructure.mappers.EntityMapper;
import com.namgrengaw.basilisk.application.product.adapters.output.entity.ProductEntity;
import com.namgrengaw.basilisk.application.product.adapters.output.repository.ProductRepository;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.output.GetProductsByStatusOutputGateway;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductsByStatusService implements GetProductsByStatusOutputGateway {

    private final ProductRepository productRepository;
    private final EntityMapper<Product, ProductEntity> mapper;

    public GetProductsByStatusService(ProductRepository productRepository,
                                      EntityMapper<Product, ProductEntity> mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Product> getProductsByStatus(boolean active) {
        final List<ProductEntity> foundProducts = productRepository.findAllByStatus(active).orElseThrow();
        return foundProducts.stream()
                .map(mapper::toDomain)
                .toList();
    }
}
