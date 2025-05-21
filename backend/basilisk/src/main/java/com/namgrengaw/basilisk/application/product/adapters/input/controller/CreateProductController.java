package com.namgrengaw.basilisk.application.product.adapters.input.controller;

import com.namgrengaw.basilisk.application.infrastructure.mappers.DtoMapper;
import com.namgrengaw.basilisk.application.product.adapters.input.dto.ProductDto;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.output.CreateProductInputGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/catalog/product")
public class CreateProductController {

    private final CreateProductInputGateway createProductInputGateway;
    private final DtoMapper<Product, ProductDto> productMapper;

    public CreateProductController(CreateProductInputGateway createProductInputGateway, DtoMapper<Product, ProductDto> productMapper) {
        this.createProductInputGateway = createProductInputGateway;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        final Product product = productMapper.toDomain(productDto);
        final Product persistedProduct = createProductInputGateway.create(product);
        final ProductDto persistedDto = productMapper.toDto(persistedProduct);
        return ResponseEntity.ok(persistedDto);
    }

}
