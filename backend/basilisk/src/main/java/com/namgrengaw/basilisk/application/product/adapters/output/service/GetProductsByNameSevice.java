package com.namgrengaw.basilisk.application.product.adapters.output.service;

import com.namgrengaw.basilisk.application.infrastructure.components.pagination.PaginatedResponse;
import com.namgrengaw.basilisk.application.infrastructure.components.pagination.PaginationRequest;
import com.namgrengaw.basilisk.application.infrastructure.mappers.EntityMapper;
import com.namgrengaw.basilisk.application.infrastructure.util.pagination.PaginationUtils;
import com.namgrengaw.basilisk.application.product.adapters.output.entity.ProductEntity;
import com.namgrengaw.basilisk.application.product.adapters.output.repository.ProductRepository;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.output.GetProductsByNameOutputGateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public PaginatedResponse<Product> getProductsByName(String name, PaginationRequest paginationRequest) {
        final PageRequest pageRequest = PaginationUtils.mapToPageRequest(paginationRequest);
        final Page<ProductEntity> result = productRepository.findAllByName(name, pageRequest);
        final List<Product> foundDomains = result.getContent().stream()
                .map(productMapper::toDomain)
                .toList();
        return PaginationUtils.mapToPaginatedResponse(foundDomains, result);
    }
}
