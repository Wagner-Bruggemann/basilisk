package com.namgrengaw.basilisk.application.product.adapters.output.service;

import com.namgrengaw.basilisk.application.product.adapters.output.repository.ProductRepository;
import com.namgrengaw.basilisk.application.product.core.ports.output.DeleteProductByIdOutputGateway;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductByIdService implements DeleteProductByIdOutputGateway {

    private final ProductRepository productRepository;

    public DeleteProductByIdService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void deleteProductById(String value) {
        productRepository.deleteById(value);
    }
}
