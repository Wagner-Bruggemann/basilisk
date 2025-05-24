package com.namgrengaw.basilisk.application.product.core.usecases;

import com.namgrengaw.basilisk.application.infrastructure.components.Status;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.input.GetProductsByStatusInputGateway;
import com.namgrengaw.basilisk.application.product.core.ports.output.GetProductsByStatusOutputGateway;

import java.util.List;

public class GetProductsByStatusUsecase implements GetProductsByStatusInputGateway {

    private final GetProductsByStatusOutputGateway getProductsByStatusOutputGateway;

    public GetProductsByStatusUsecase(GetProductsByStatusOutputGateway getProductsByStatusOutputGateway) {
        this.getProductsByStatusOutputGateway = getProductsByStatusOutputGateway;
    }

    @Override
    public List<Product> getProductsByStatus(Status status) {
        return getProductsByStatusOutputGateway.getProductsByStatus(status.isActive());
    }

}
