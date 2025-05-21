package com.namgrengaw.basilisk.application.product.core.usecases;

import com.namgrengaw.basilisk.application.infrastructure.components.Identifier;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.input.DeleteProductByIdInputGateway;
import com.namgrengaw.basilisk.application.product.core.ports.output.DeleteProductByIdOutputGateway;
import com.namgrengaw.basilisk.application.product.core.ports.output.GetProductByIdOutputGateway;

public class DeleteProductByIdUsecase implements DeleteProductByIdInputGateway {

    private final DeleteProductByIdOutputGateway deleteProductByIdOutputGateway;
    private final GetProductByIdOutputGateway getProductByIdOutputGateway;

    public DeleteProductByIdUsecase(DeleteProductByIdOutputGateway deleteProductByIdOutputGateway,
                                    GetProductByIdOutputGateway getProductByIdOutputGateway) {
        this.deleteProductByIdOutputGateway = deleteProductByIdOutputGateway;
        this.getProductByIdOutputGateway = getProductByIdOutputGateway;
    }

    @Override
    public Product deleteProductById(Identifier id) {
        Product foundProduct = getProductByIdOutputGateway.getById(id.getValue());
        deleteProductByIdOutputGateway.deleteProductById(id.getValue());
        return foundProduct;
    }

}
