package com.namgrengaw.basilisk.application.product.core.usecases;

import com.namgrengaw.basilisk.application.infrastructure.components.Identifier;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.input.UpdateProductInputGateway;
import com.namgrengaw.basilisk.application.product.core.ports.output.GetProductByIdOutputGateway;
import com.namgrengaw.basilisk.application.product.core.ports.output.UpdateProductOutputGateway;

public class UpdateProductUsecase implements UpdateProductInputGateway {

    private final UpdateProductOutputGateway updateProductOutputGateway;
    private final GetProductByIdOutputGateway getProductByIdOutputGateway;

    public UpdateProductUsecase(UpdateProductOutputGateway updateProductOutputGateway, GetProductByIdOutputGateway getProductByIdOutputGateway) {
        this.updateProductOutputGateway = updateProductOutputGateway;
        this.getProductByIdOutputGateway = getProductByIdOutputGateway;
    }

    @Override
    public Product updateProduct(Identifier id, Product product) {
        final Product foundProduct = getProductByIdOutputGateway.getById(id.getValue());
        foundProduct.updateName(product.getName());
        foundProduct.updateDescription(product.getDescription());
        foundProduct.changeStatus(product.getStatus());
        return updateProductOutputGateway.update(foundProduct);
    }

}
