package com.namgrengaw.basilisk.application.product.adapters.input.controller;

import com.namgrengaw.basilisk.application.infrastructure.components.Identifier;
import com.namgrengaw.basilisk.application.infrastructure.mappers.DtoMapper;
import com.namgrengaw.basilisk.application.product.adapters.input.dto.ProductDto;
import com.namgrengaw.basilisk.application.product.adapters.input.hateaos.BasicHateaosSetupLinks;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.input.DeleteProductByIdInputGateway;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/catalog/product")
@Tag(name = "Catalog", description = "Catalog operations")
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
    @Operation(
            summary = "Delete a product by id",
            description = "Delete a product by identifier",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Has successfully deleted the product"),
                    @ApiResponse(responseCode = "404",
                            description = "Couldn't delete the product because haven't found it")
            }
    )
    public ResponseEntity<ProductDto> deleteProductById(@PathVariable String id) {
        final Identifier identifier = new Identifier(id);
        Product deletedProduct = deleteProductByIdInputGateway.deleteProductById(identifier);
        ProductDto deletedDto = mapper.toDto(deletedProduct);

        configureHateaos(deletedDto);

        return ResponseEntity.ok(deletedDto);
    }

    private static void configureHateaos(ProductDto deletedDto) {
        BasicHateaosSetupLinks.setupGetAll(deletedDto);
        BasicHateaosSetupLinks.setupCreate(deletedDto);

        deletedDto.add(WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(DeleteProductByIdController .class)
                                .deleteProductById(deletedDto.getId())
                ).withSelfRel()
        );
    }

}
