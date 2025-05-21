package com.namgrengaw.basilisk.application.product.core.ports.input;

import com.namgrengaw.basilisk.application.infrastructure.components.Identifier;
import com.namgrengaw.basilisk.application.product.core.domain.Product;

public interface UpdateProductInputGateway {
    Product updateProduct(Identifier id, Product product);
}
