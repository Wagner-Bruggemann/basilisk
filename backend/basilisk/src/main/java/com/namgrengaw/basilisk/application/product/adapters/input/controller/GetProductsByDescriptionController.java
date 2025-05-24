package com.namgrengaw.basilisk.application.product.adapters.input.controller;

import com.namgrengaw.basilisk.application.infrastructure.components.Description;
import com.namgrengaw.basilisk.application.infrastructure.mappers.DtoMapper;
import com.namgrengaw.basilisk.application.product.adapters.input.dto.ProductDto;
import com.namgrengaw.basilisk.application.product.adapters.input.hateaos.BasicHateaosSetupLinks;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.input.GetProductsByDescriptionInputGateway;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/catalog/product")
@Tag(name = "Catalog", description = "Catalog operations")
public class GetProductsByDescriptionController {

    private final GetProductsByDescriptionInputGateway getProductsByDescriptionInputGateway;
    private final DtoMapper<Product, ProductDto> productMapper;

    public GetProductsByDescriptionController(
            final GetProductsByDescriptionInputGateway getProductsByDescriptionInputGateway,
            final DtoMapper<Product, ProductDto> productMapper
    ) {
        this.getProductsByDescriptionInputGateway = getProductsByDescriptionInputGateway;
        this.productMapper = productMapper;
    }

    @GetMapping("/with-description/{description}")
    @Operation(
            summary = "Get by Description",
            description = "Find all products by it's like description",
            responses = {
                @ApiResponse(responseCode = "200", description = "Products found"),
                @ApiResponse(responseCode = "404", description = "Products not found")
            }
    )
    public ResponseEntity<List<ProductDto>> getProductByDescription(@PathVariable final String description) {
        final Description productDescription = new Description(description);
        final List<Product> foundProducts = getProductsByDescriptionInputGateway.getProductsByDescription(productDescription);
        final List<ProductDto> foundDtos = foundProducts.stream()
                .map(productMapper::toDto)
                .toList();

        foundDtos.forEach(GetProductsByDescriptionController::configureHateaos);

        return ResponseEntity.ok(foundDtos);
    }

    private static void configureHateaos(ProductDto productDto) {
//        BasicHateaosSetupLinks.setupGetAll(productDto);
        BasicHateaosSetupLinks.setupGetById(productDto);
        BasicHateaosSetupLinks.setupGetByName(productDto);
        BasicHateaosSetupLinks.setupGetByStatus(productDto);
        BasicHateaosSetupLinks.setupCreate(productDto);
        BasicHateaosSetupLinks.setupUpdate(productDto);
        BasicHateaosSetupLinks.setupDelete(productDto);

        productDto.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(GetProductsByDescriptionController.class)
                        .getProductByDescription(productDto.getDescription())
            ).withSelfRel()
        );
    }

}
