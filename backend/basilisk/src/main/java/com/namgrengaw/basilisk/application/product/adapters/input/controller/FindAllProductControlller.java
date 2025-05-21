package com.namgrengaw.basilisk.application.product.adapters.input.controller;

import com.namgrengaw.basilisk.application.infrastructure.mappers.DtoMapper;
import com.namgrengaw.basilisk.application.product.adapters.input.dto.ProductDto;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.input.FindAllProductsInputGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/catalog/product")
public class FindAllProductControlller {

    private final FindAllProductsInputGateway findAllProductsInputGateway;
    private final DtoMapper<Product, ProductDto> mapper;

    public FindAllProductControlller(
            FindAllProductsInputGateway findAllProductsInputGateway,
            DtoMapper<Product, ProductDto> mapper
    ) {
        this.findAllProductsInputGateway = findAllProductsInputGateway;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAllProduct() {
        final List<Product> foundProducts = findAllProductsInputGateway.findAllProducts();

        final List<ProductDto> foundDtos = foundProducts.stream()
                .map(mapper::toDto)
                .toList();

        return ResponseEntity.ok(foundDtos);
    }

}
