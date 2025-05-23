package com.namgrengaw.basilisk.application.product.adapters.input.controller;

import com.namgrengaw.basilisk.application.infrastructure.mappers.DtoMapper;
import com.namgrengaw.basilisk.application.product.adapters.input.dto.ProductDto;
import com.namgrengaw.basilisk.application.product.adapters.input.hateaos.BasicHateaosSetupLinks;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.input.FindAllProductsInputGateway;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/catalog/product")
@Tag(name = "Catalog", description = "Catalog operations")
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
    @Operation(
            summary = "Get all products",
            description = "Find all product available",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Found at least one product"),
                    @ApiResponse(responseCode = "404", description = "Doesn't found any products")
            }
    )
    public ResponseEntity<List<ProductDto>> findAllProduct() {
        final List<Product> foundProducts = findAllProductsInputGateway.findAllProducts();

        final List<ProductDto> foundDtos = foundProducts.stream()
                .map(mapper::toDto)
                .toList();

        configureHateaos(foundDtos);

        return ResponseEntity.ok(foundDtos);
    }

    private static void configureHateaos(List<ProductDto> foundDtos) {
        foundDtos.forEach(dto -> {
            BasicHateaosSetupLinks.setupGetById(dto);
            BasicHateaosSetupLinks.setupCreate(dto);
            BasicHateaosSetupLinks.setupUpdate(dto);
            BasicHateaosSetupLinks.setupDelete(dto);

            dto.add(WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(FindAllProductControlller.class)
                                    .findAllProduct()
                    ).withSelfRel());
            });
    }

}
