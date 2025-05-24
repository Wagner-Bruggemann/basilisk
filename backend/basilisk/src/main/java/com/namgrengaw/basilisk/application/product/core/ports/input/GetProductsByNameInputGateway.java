package com.namgrengaw.basilisk.application.product.core.ports.input;

import com.namgrengaw.basilisk.application.infrastructure.components.Name;
import com.namgrengaw.basilisk.application.product.core.domain.Product;

import java.util.List;

public interface GetProductsByNameInputGateway {
    List<Product> getProductsByName(Name name);
}
