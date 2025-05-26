package com.namgrengaw.basilisk.application.product.core.ports.output;

import com.namgrengaw.basilisk.application.infrastructure.components.pagination.PaginatedResponse;
import com.namgrengaw.basilisk.application.infrastructure.components.pagination.PaginationRequest;
import com.namgrengaw.basilisk.application.product.core.domain.Product;

public interface GetProductsByNameOutputGateway {
    PaginatedResponse<Product> getProductsByName(String name, PaginationRequest paginationRequest);
}
