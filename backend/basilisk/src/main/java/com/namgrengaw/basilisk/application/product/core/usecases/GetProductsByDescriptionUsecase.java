package com.namgrengaw.basilisk.application.product.core.usecases;

import com.namgrengaw.basilisk.application.infrastructure.components.Description;
import com.namgrengaw.basilisk.application.infrastructure.components.pagination.PaginatedResponse;
import com.namgrengaw.basilisk.application.infrastructure.components.pagination.PaginationRequest;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.input.GetProductsByDescriptionInputGateway;
import com.namgrengaw.basilisk.application.product.core.ports.output.GetProductsByDescriptionOutputGateway;

import java.util.List;

public class GetProductsByDescriptionUsecase implements GetProductsByDescriptionInputGateway {

    private final GetProductsByDescriptionOutputGateway getProductsByDescriptionOutputGateway;

    public GetProductsByDescriptionUsecase(GetProductsByDescriptionOutputGateway getProductsByDescriptionOutputGateway) {
        this.getProductsByDescriptionOutputGateway = getProductsByDescriptionOutputGateway;
    }

    @Override
    public PaginatedResponse<Product> getProductsByDescription(Description description, PaginationRequest paginationRequest) {
        return getProductsByDescriptionOutputGateway.getProductsByDescription(description.getValue(), paginationRequest);
    }
}
