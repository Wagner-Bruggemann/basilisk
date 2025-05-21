package com.namgrengaw.basilisk.application.product.core.ports.output;

import com.namgrengaw.basilisk.application.product.core.domain.Product;

public interface CreateProductInputGateway {
    Product create(Product product);
}
