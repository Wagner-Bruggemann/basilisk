package com.namgrengaw.basilisk.application.product.core.ports.input;

import com.namgrengaw.basilisk.application.infrastructure.components.Description;
import com.namgrengaw.basilisk.application.product.core.domain.Product;

import java.util.List;

public interface GetProductsByDescriptionInputGateway {
    List<Product> getProductsByDescription(Description description);
}
