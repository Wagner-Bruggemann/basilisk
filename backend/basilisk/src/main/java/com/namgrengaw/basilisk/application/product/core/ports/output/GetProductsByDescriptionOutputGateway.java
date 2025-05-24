package com.namgrengaw.basilisk.application.product.core.ports.output;

import com.namgrengaw.basilisk.application.product.core.domain.Product;

import java.util.List;

public interface GetProductsByDescriptionOutputGateway {
    List<Product> getProductsByDescription(String description);
}
