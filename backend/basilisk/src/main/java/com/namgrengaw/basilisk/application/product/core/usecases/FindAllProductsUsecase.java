package com.namgrengaw.basilisk.application.product.core.usecases;

import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.input.FindAllProductsInputGateway;
import com.namgrengaw.basilisk.application.product.core.ports.output.FindAllProductsOutputGateway;

import java.util.List;

public class FindAllProductsUsecase implements FindAllProductsInputGateway {

    private final FindAllProductsOutputGateway findAllProductsOutputGateway;

    public FindAllProductsUsecase(FindAllProductsOutputGateway findAllProductsOutputGateway) {
        this.findAllProductsOutputGateway = findAllProductsOutputGateway;
    }

    @Override
    public List<Product> findAllProducts() {
        return this.findAllProductsOutputGateway.findAllProducts();
    }
}
