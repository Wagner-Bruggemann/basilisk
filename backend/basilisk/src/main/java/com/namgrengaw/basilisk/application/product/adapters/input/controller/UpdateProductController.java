package com.namgrengaw.basilisk.application.product.adapters.input.controller;

import com.namgrengaw.basilisk.application.infrastructure.components.Identifier;
import com.namgrengaw.basilisk.application.infrastructure.mappers.DtoMapper;
import com.namgrengaw.basilisk.application.product.adapters.input.dto.ProductDto;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.input.UpdateProductInputGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/catalog/product")
public class UpdateProductController {

    private final UpdateProductInputGateway updateProductInputGateway;
    private final DtoMapper<Product, ProductDto> mapper;

    public UpdateProductController(UpdateProductInputGateway updateProductInputGateway, DtoMapper<Product, ProductDto> mapper) {
        this.updateProductInputGateway = updateProductInputGateway;
        this.mapper = mapper;
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String id, @RequestBody ProductDto productDto) {
        final Identifier identifier = new Identifier(id);
        final Product product = mapper.toDomain(productDto);
        final Product updatedProduct = updateProductInputGateway.updateProduct(identifier, product);
        final ProductDto updatedDto = mapper.toDto(updatedProduct);
        return ResponseEntity.ok(updatedDto);
    }

}
