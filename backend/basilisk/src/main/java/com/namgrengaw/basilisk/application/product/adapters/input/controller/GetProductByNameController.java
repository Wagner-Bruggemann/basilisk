package com.namgrengaw.basilisk.application.product.adapters.input.controller;

import com.namgrengaw.basilisk.application.infrastructure.components.Name;
import com.namgrengaw.basilisk.application.infrastructure.mappers.DtoMapper;
import com.namgrengaw.basilisk.application.product.adapters.input.dto.ProductDto;
import com.namgrengaw.basilisk.application.product.adapters.input.hateaos.BasicHateaosSetupLinks;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.input.GetProductByIdInputGateway;
import com.namgrengaw.basilisk.application.product.core.ports.input.GetProductsByNameInputGateway;
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
public class GetProductByNameController {

    private final GetProductsByNameInputGateway getProductsByNameInputGateway;
    private final DtoMapper<Product, ProductDto> productMapper;

    public GetProductByNameController(
            final GetProductsByNameInputGateway getProductsByNameInputGateway,
            final DtoMapper<Product, ProductDto> productMapper
    ) {
        this.getProductsByNameInputGateway = getProductsByNameInputGateway;
        this.productMapper = productMapper;
    }

    @GetMapping("/with-name/{name}")
    @Operation(
            summary = "Get by Name",
            description = "Find all products by it's like name",
            responses = {
                @ApiResponse(responseCode = "200", description = "Products found"),
                @ApiResponse(responseCode = "404", description = "Products not found")
            }
    )
    public ResponseEntity<List<ProductDto>> getProductByName(@PathVariable final String name) {
        final Name productName = new Name(name);
        final List<Product> foundProducts = getProductsByNameInputGateway.getProductsByName(productName);
        final List<ProductDto> foundDtos = foundProducts.stream()
                .map(productMapper::toDto)
                .toList();

        foundDtos.forEach(GetProductByNameController::configureHateaos);

        return ResponseEntity.ok(foundDtos);
    }

    private static void configureHateaos(ProductDto productDto) {
//        BasicHateaosSetupLinks.setupGetAll(productDto);
        BasicHateaosSetupLinks.setupGetById(productDto);
        BasicHateaosSetupLinks.setupGetByDescription(productDto);
        BasicHateaosSetupLinks.setupGetByStatus(productDto);
        BasicHateaosSetupLinks.setupCreate(productDto);
        BasicHateaosSetupLinks.setupUpdate(productDto);
        BasicHateaosSetupLinks.setupDelete(productDto);

        productDto.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(GetProductByNameController.class)
                        .getProductByName(productDto.getName())
            ).withSelfRel()
        );
    }

}
