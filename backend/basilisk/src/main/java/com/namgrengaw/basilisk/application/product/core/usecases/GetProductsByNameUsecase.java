package com.namgrengaw.basilisk.application.product.core.usecases;

import com.namgrengaw.basilisk.application.infrastructure.components.Name;
import com.namgrengaw.basilisk.application.infrastructure.components.pagination.PaginatedResponse;
import com.namgrengaw.basilisk.application.infrastructure.components.pagination.PaginationRequest;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.input.GetProductsByNameInputGateway;
import com.namgrengaw.basilisk.application.product.core.ports.output.GetProductsByNameOutputGateway;

import java.util.List;

public class GetProductsByNameUsecase implements GetProductsByNameInputGateway {

    private final GetProductsByNameOutputGateway getProductsByNameOutputGateway;

    public GetProductsByNameUsecase(GetProductsByNameOutputGateway getProductsByNameOutputGateway) {
        this.getProductsByNameOutputGateway = getProductsByNameOutputGateway;
    }

    @Override
    public PaginatedResponse<Product> getProductsByName(Name name, PaginationRequest paginationRequest) {
        return getProductsByNameOutputGateway.getProductsByName(name.getValue(), paginationRequest);
    }


}
