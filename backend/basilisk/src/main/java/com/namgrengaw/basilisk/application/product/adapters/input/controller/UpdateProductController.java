package com.namgrengaw.basilisk.application.product.adapters.input.controller;

import com.namgrengaw.basilisk.application.infrastructure.components.Identifier;
import com.namgrengaw.basilisk.application.infrastructure.mappers.DtoMapper;
import com.namgrengaw.basilisk.application.product.adapters.input.dto.ProductDto;
import com.namgrengaw.basilisk.application.product.adapters.input.hateaos.BasicHateaosSetupLinks;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.input.UpdateProductInputGateway;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/catalog/product")
@Tag(name = "Catalog", description = "Catalog operations")
public class UpdateProductController {

    private final UpdateProductInputGateway updateProductInputGateway;
    private final DtoMapper<Product, ProductDto> mapper;

    public UpdateProductController(UpdateProductInputGateway updateProductInputGateway, DtoMapper<Product, ProductDto> mapper) {
        this.updateProductInputGateway = updateProductInputGateway;
        this.mapper = mapper;
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Update a product",
            description = "Update a product by passing the updated data via body and id via path variable",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Updated the product"),
                    @ApiResponse(responseCode = "404", description = "Couldn't update the product")
            }
    )
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String id, @RequestBody ProductDto productDto) {
        final Identifier identifier = new Identifier(id);
        final Product product = mapper.toDomain(productDto);
        final Product updatedProduct = updateProductInputGateway.updateProduct(identifier, product);
        final ProductDto updatedDto = mapper.toDto(updatedProduct);

        configureHateaos(updatedDto);

        return ResponseEntity.ok(updatedDto);
    }

    private static void configureHateaos(ProductDto updatedDto) {
        BasicHateaosSetupLinks.setupGetAll(updatedDto);
        BasicHateaosSetupLinks.setupGetById(updatedDto);
        BasicHateaosSetupLinks.setupCreate(updatedDto);
        BasicHateaosSetupLinks.setupDelete(updatedDto);

        updatedDto.add(WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(UpdateProductController .class)
                                .updateProduct(updatedDto.getId(), updatedDto)
                ).withSelfRel()
        );
    }

}
