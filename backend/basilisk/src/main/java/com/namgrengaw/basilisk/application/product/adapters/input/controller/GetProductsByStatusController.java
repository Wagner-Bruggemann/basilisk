package com.namgrengaw.basilisk.application.product.adapters.input.controller;

import com.namgrengaw.basilisk.application.infrastructure.components.Description;
import com.namgrengaw.basilisk.application.infrastructure.components.Status;
import com.namgrengaw.basilisk.application.infrastructure.mappers.DtoMapper;
import com.namgrengaw.basilisk.application.product.adapters.input.dto.ProductDto;
import com.namgrengaw.basilisk.application.product.adapters.input.hateaos.BasicHateaosSetupLinks;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.input.GetProductsByDescriptionInputGateway;
import com.namgrengaw.basilisk.application.product.core.ports.input.GetProductsByStatusInputGateway;
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
public class GetProductsByStatusController {

    private final GetProductsByStatusInputGateway getProductsByStatusInputGateway;
    private final DtoMapper<Product, ProductDto> productMapper;

    public GetProductsByStatusController(
            final GetProductsByStatusInputGateway getProductsByStatusInputGateway,
            final DtoMapper<Product, ProductDto> productMapper
    ) {
        this.getProductsByStatusInputGateway = getProductsByStatusInputGateway;
        this.productMapper = productMapper;
    }

    @GetMapping("/with-status/{status}")
    @Operation(
            summary = "Get by Status",
            description = "Find all products by it's status",
            responses = {
                @ApiResponse(responseCode = "200", description = "Products found"),
                @ApiResponse(responseCode = "404", description = "Products not found")
            }
    )
    public ResponseEntity<List<ProductDto>> getProductByStatus(@PathVariable final boolean status) {
        final Status productStatus = new Status(status);
        final List<Product> foundProducts = getProductsByStatusInputGateway.getProductsByStatus(productStatus);
        final List<ProductDto> foundDtos = foundProducts.stream()
                .map(productMapper::toDto)
                .toList();

        foundDtos.forEach(GetProductsByStatusController::configureHateaos);

        return ResponseEntity.ok(foundDtos);
    }

    private static void configureHateaos(ProductDto productDto) {
//        BasicHateaosSetupLinks.setupGetAll(productDto);
        BasicHateaosSetupLinks.setupGetById(productDto);
        BasicHateaosSetupLinks.setupGetByName(productDto);
        BasicHateaosSetupLinks.setupGetByDescription(productDto);
        BasicHateaosSetupLinks.setupCreate(productDto);
        BasicHateaosSetupLinks.setupUpdate(productDto);
        BasicHateaosSetupLinks.setupDelete(productDto);

        productDto.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(GetProductsByStatusController.class)
                        .getProductByStatus(productDto.getStatus())
            ).withSelfRel()
        );
    }

}
