package com.namgrengaw.basilisk.application.product.adapters.output.service;

import com.namgrengaw.basilisk.application.infrastructure.components.pagination.PaginatedResponse;
import com.namgrengaw.basilisk.application.infrastructure.components.pagination.PaginationRequest;
import com.namgrengaw.basilisk.application.infrastructure.mappers.EntityMapper;
import com.namgrengaw.basilisk.application.infrastructure.util.pagination.PaginationUtils;
import com.namgrengaw.basilisk.application.product.adapters.output.entity.ProductEntity;
import com.namgrengaw.basilisk.application.product.adapters.output.repository.ProductRepository;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.output.GetProductsByStatusOutputGateway;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public PaginatedResponse<Product> getProductsByStatus(boolean active, PaginationRequest paginationRequest) {
        final PageRequest pageRequest = PaginationUtils.mapToPageRequest(paginationRequest);
        final Page<ProductEntity> result = productRepository.findAllByStatus(active, pageRequest);
        final List<Product> foundDomains = result.stream()
                .map(mapper::toDomain)
                .toList();
        return PaginationUtils.mapToPaginatedResponse(foundDomains, result);
    }
}
