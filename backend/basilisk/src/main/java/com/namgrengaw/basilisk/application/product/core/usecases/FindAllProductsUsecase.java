package com.namgrengaw.basilisk.application.product.core.usecases;

import com.namgrengaw.basilisk.application.infrastructure.components.pagination.PaginatedResponse;
import com.namgrengaw.basilisk.application.infrastructure.components.pagination.PaginationRequest;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.input.FindAllProductsInputGateway;
import com.namgrengaw.basilisk.application.product.core.ports.output.FindAllProductsOutputGateway;

public class FindAllProductsUsecase implements FindAllProductsInputGateway {

    private final FindAllProductsOutputGateway findAllProductsOutputGateway;

    public FindAllProductsUsecase(FindAllProductsOutputGateway findAllProductsOutputGateway) {
        this.findAllProductsOutputGateway = findAllProductsOutputGateway;
    }

    @Override
    public PaginatedResponse<Product> findAllProducts(PaginationRequest paginationRequest) {
        return this.findAllProductsOutputGateway.findAllProducts(paginationRequest);
    }
}
