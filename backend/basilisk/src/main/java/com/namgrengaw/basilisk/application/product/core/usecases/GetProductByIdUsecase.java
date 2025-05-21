package com.namgrengaw.basilisk.application.product.core.usecases;

import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.input.GetProductByIdInputGateway;
import com.namgrengaw.basilisk.application.product.core.ports.output.GetProductByIdOutputGateway;

public class GetProductByIdUsecase implements GetProductByIdInputGateway {

    private final GetProductByIdOutputGateway getProductByIdOutputGateway;

    public GetProductByIdUsecase(GetProductByIdOutputGateway getProductByIdOutputGateway) {
        this.getProductByIdOutputGateway = getProductByIdOutputGateway;
    }

    @Override
    public Product getProductById(String id) {
        return getProductByIdOutputGateway.getById(id);
    }

}
