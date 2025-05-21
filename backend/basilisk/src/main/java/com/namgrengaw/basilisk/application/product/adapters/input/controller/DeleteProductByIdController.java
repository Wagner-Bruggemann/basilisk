package com.namgrengaw.basilisk.application.product.adapters.input.controller;

import com.namgrengaw.basilisk.application.infrastructure.components.Identifier;
import com.namgrengaw.basilisk.application.infrastructure.mappers.DtoMapper;
import com.namgrengaw.basilisk.application.product.adapters.input.dto.ProductDto;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.input.DeleteProductByIdInputGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/catalog/product")
public class DeleteProductByIdController {

    private final DeleteProductByIdInputGateway deleteProductByIdInputGateway;
    private final DtoMapper<Product, ProductDto> mapper;

    public DeleteProductByIdController(
            DeleteProductByIdInputGateway deleteProductByIdInputGateway,
            DtoMapper<Product, ProductDto> mapper
    ) {
        this.deleteProductByIdInputGateway = deleteProductByIdInputGateway;
        this.mapper = mapper;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ProductDto> deleteProductById(@PathVariable String id) {
        final Identifier identifier = new Identifier(id);
        Product deletedProduct = deleteProductByIdInputGateway.deleteProductById(identifier);
        ProductDto deletedDto = mapper.toDto(deletedProduct);
        return ResponseEntity.ok(deletedDto);
    }

}
