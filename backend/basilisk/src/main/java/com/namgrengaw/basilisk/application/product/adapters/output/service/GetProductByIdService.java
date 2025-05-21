package com.namgrengaw.basilisk.application.product.adapters.output.service;

import com.namgrengaw.basilisk.application.infrastructure.mappers.EntityMapper;
import com.namgrengaw.basilisk.application.product.adapters.output.entity.ProductEntity;
import com.namgrengaw.basilisk.application.product.adapters.output.repository.ProductRepository;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.output.GetProductByIdOutputGateway;
import org.springframework.stereotype.Service;

@Service
public class GetProductByIdService implements GetProductByIdOutputGateway {

    private ProductRepository productRepository;
    private EntityMapper<Product, ProductEntity> productMapper;

    public GetProductByIdService(ProductRepository productRepository, EntityMapper<Product, ProductEntity> productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Product getById(String id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow();
        return productMapper.toDomain(productEntity);
    }
}
