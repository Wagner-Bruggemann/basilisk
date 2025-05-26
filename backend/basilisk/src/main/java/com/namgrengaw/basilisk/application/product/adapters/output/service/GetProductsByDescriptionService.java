package com.namgrengaw.basilisk.application.product.adapters.output.service;

import com.namgrengaw.basilisk.application.infrastructure.components.pagination.PaginatedResponse;
import com.namgrengaw.basilisk.application.infrastructure.components.pagination.PaginationRequest;
import com.namgrengaw.basilisk.application.infrastructure.mappers.EntityMapper;
import com.namgrengaw.basilisk.application.infrastructure.util.pagination.PaginationUtils;
import com.namgrengaw.basilisk.application.product.adapters.output.entity.ProductEntity;
import com.namgrengaw.basilisk.application.product.adapters.output.repository.ProductRepository;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.output.GetProductsByDescriptionOutputGateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductsByDescriptionService implements GetProductsByDescriptionOutputGateway {

    private final ProductRepository productRepository;
    private final EntityMapper<Product, ProductEntity> mapper;

    public GetProductsByDescriptionService(ProductRepository productRepository, EntityMapper<Product, ProductEntity> mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public PaginatedResponse<Product> getProductsByDescription(String description, PaginationRequest paginationRequest) {
        final PageRequest pageRequest = PaginationUtils.mapToPageRequest(paginationRequest);
        final Page<ProductEntity> result = productRepository.findAllByDescription(description, pageRequest);
        final List<Product> foundDomains = result.getContent().stream()
                .map(mapper::toDomain)
                .toList();
        return PaginationUtils.mapToPaginatedResponse(foundDomains, result);
    }
}
