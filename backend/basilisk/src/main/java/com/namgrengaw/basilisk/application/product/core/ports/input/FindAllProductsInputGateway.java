package com.namgrengaw.basilisk.application.product.core.ports.input;

import com.namgrengaw.basilisk.application.infrastructure.components.pagination.PaginatedResponse;
import com.namgrengaw.basilisk.application.infrastructure.components.pagination.PaginationRequest;
import com.namgrengaw.basilisk.application.product.core.domain.Product;

public interface FindAllProductsInputGateway {
    PaginatedResponse<Product> findAllProducts(PaginationRequest paginationRequest);
}
