package com.namgrengaw.basilisk.application.product.core.usecases;

import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.input.CreateProductOutputGateway;
import com.namgrengaw.basilisk.application.product.core.ports.output.CreateProductInputGateway;

public class CreateProductUsecase implements CreateProductInputGateway {

    private final CreateProductOutputGateway createProductOutputGateway;

    public CreateProductUsecase(CreateProductOutputGateway createProductOutputGateway) {
        this.createProductOutputGateway = createProductOutputGateway;
    }

    @Override
    public Product create(Product product) {
        return createProductOutputGateway.create(product);
    }

}
