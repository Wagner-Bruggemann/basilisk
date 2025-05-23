package com.namgrengaw.basilisk.application.product.adapters.input.controller;

import com.namgrengaw.basilisk.application.infrastructure.mappers.DtoMapper;
import com.namgrengaw.basilisk.application.product.adapters.input.dto.ProductDto;
import com.namgrengaw.basilisk.application.product.adapters.input.hateaos.BasicHateaosSetupLinks;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.output.CreateProductInputGateway;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/catalog/product")
@Tag(name = "Catalog", description = "Catalog operations")
public class CreateProductController {

    private final CreateProductInputGateway createProductInputGateway;
    private final DtoMapper<Product, ProductDto> productMapper;

    public CreateProductController(CreateProductInputGateway createProductInputGateway, DtoMapper<Product, ProductDto> productMapper) {
        this.createProductInputGateway = createProductInputGateway;
        this.productMapper = productMapper;
    }

    @PostMapping
    @Operation(
            summary = "Create a product",
            description = "Create a product by passing it via body requisition",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Product have been created"),
                    @ApiResponse(responseCode = "404", description = "Couldn't create the product")
            }
    )
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        final Product product = productMapper.toDomain(productDto);
        final Product persistedProduct = createProductInputGateway.create(product);
        final ProductDto persistedDto = productMapper.toDto(persistedProduct);

        configureHateaos(persistedDto);

        return ResponseEntity.ok(persistedDto);
    }

    private static void configureHateaos(ProductDto persistedDto) {
        BasicHateaosSetupLinks.setupGetAll(persistedDto);
        BasicHateaosSetupLinks.setupGetById(persistedDto);
        BasicHateaosSetupLinks.setupUpdate(persistedDto);
        BasicHateaosSetupLinks.setupDelete(persistedDto);

        persistedDto.add(WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(CreateProductController .class)
                                .createProduct(persistedDto)
                ).withSelfRel()
        );
    }

}
