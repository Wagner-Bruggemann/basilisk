package com.namgrengaw.basilisk.application.product.core.ports.input;

import com.namgrengaw.basilisk.application.product.core.domain.Product;

public interface CreateProductOutputGateway {
    Product create(Product product);
}
