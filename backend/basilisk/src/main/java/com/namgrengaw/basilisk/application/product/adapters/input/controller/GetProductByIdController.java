package com.namgrengaw.basilisk.application.product.adapters.input.controller;

import com.namgrengaw.basilisk.application.infrastructure.mappers.DtoMapper;
import com.namgrengaw.basilisk.application.product.adapters.input.dto.ProductDto;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.input.GetProductByIdInputGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/catalog/product")
public class GetProductByIdController {

    private final GetProductByIdInputGateway getProductByIdInputGateway;
    private final DtoMapper<Product, ProductDto> productMapper;

    public GetProductByIdController(
            final GetProductByIdInputGateway getProductByIdInputGateway,
            final DtoMapper<Product, ProductDto> productMapper
    ) {
        this.getProductByIdInputGateway = getProductByIdInputGateway;
        this.productMapper = productMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable final String id) {
        final Product foundProduct = getProductByIdInputGateway.getProductById(id);
        final ProductDto productDto = productMapper.toDto(foundProduct);
        return ResponseEntity.ok(productDto);
    }

}
