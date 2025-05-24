package com.namgrengaw.basilisk.application.product.core.ports.input;

import com.namgrengaw.basilisk.application.infrastructure.components.Status;
import com.namgrengaw.basilisk.application.product.core.domain.Product;

import java.util.List;

public interface GetProductsByStatusInputGateway {
    List<Product> getProductsByStatus(Status status);
}
