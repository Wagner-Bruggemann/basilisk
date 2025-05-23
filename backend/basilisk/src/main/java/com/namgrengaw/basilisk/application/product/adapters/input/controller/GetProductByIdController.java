package com.namgrengaw.basilisk.application.product.adapters.input.controller;

import com.namgrengaw.basilisk.application.infrastructure.mappers.DtoMapper;
import com.namgrengaw.basilisk.application.product.adapters.input.dto.ProductDto;
import com.namgrengaw.basilisk.application.product.adapters.input.hateaos.BasicHateaosSetupLinks;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.input.GetProductByIdInputGateway;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
@RequestMapping("/v1/catalog/product")
@Tag(name = "Catalog", description = "Catalog operations")
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
    @Operation(
            summary = "Get by ID",
            description = "Find a product by it's identifier",
            responses = {
                @ApiResponse(responseCode = "200", description = "Product found"),
                @ApiResponse(responseCode = "404", description = "Product not found")
            }
    )
    public ResponseEntity<ProductDto> getProductById(@PathVariable final String id) {
        final Product foundProduct = getProductByIdInputGateway.getProductById(id);
        final ProductDto productDto = productMapper.toDto(foundProduct);

        configureHateaos(productDto);

        return ResponseEntity.ok(productDto);
    }

    private static void configureHateaos(ProductDto productDto) {
        BasicHateaosSetupLinks.setupGetAll(productDto);
        BasicHateaosSetupLinks.setupCreate(productDto);
        BasicHateaosSetupLinks.setupUpdate(productDto);
        BasicHateaosSetupLinks.setupDelete(productDto);

        productDto.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(GetProductByIdController .class)
                        .getProductById(productDto.getId())
            ).withSelfRel()
        );
    }

}
