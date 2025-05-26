package com.namgrengaw.basilisk.application.product.adapters.input.controller;

import com.namgrengaw.basilisk.application.infrastructure.components.Description;
import com.namgrengaw.basilisk.application.infrastructure.components.pagination.PaginatedResponse;
import com.namgrengaw.basilisk.application.infrastructure.components.pagination.PaginationRequest;
import com.namgrengaw.basilisk.application.infrastructure.mappers.DtoMapper;
import com.namgrengaw.basilisk.application.infrastructure.util.pagination.PaginationUtils;
import com.namgrengaw.basilisk.application.product.adapters.input.dto.ProductDto;
import com.namgrengaw.basilisk.application.product.adapters.input.hateaos.BasicHateaosSetupLinks;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.input.GetProductsByDescriptionInputGateway;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.Link;
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
            },
            parameters = {
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "page",
                            description = "Page number (0-based)",
                            schema = @Schema(type = "integer", minimum = "0", defaultValue = "0", example = "0")
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "size",
                            description = "Number of records per page",
                            schema = @Schema(type = "integer", minimum = "1", maximum = "100", defaultValue = "10", example = "10")
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "sort",
                            description = "Sort criteria in the format: property,(asc|desc). E.g., name,desc",
                            schema = @Schema(type = "string", defaultValue = "name,asc", example = "name,desc")
                    )
            }
    )
    public ResponseEntity<PaginatedResponse<ProductDto>> getProductByDescription(
            @PathVariable final String description,
            @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.ASC)
            @Parameter(hidden = true)
            Pageable pageable
    ) {
        final Description productDescription = new Description(description);
        final PaginationRequest paginationRequest = PaginationUtils.mapToPaginationRequest(pageable);
        final PaginatedResponse<Product> paginatedResponse = getProductsByDescriptionInputGateway.getProductsByDescription(productDescription, paginationRequest);
        final List<ProductDto> foundDtos = paginatedResponse.content().stream()
                .map(productMapper::toDto)
                .toList();

        GetProductsByDescriptionController.configureHateaos(foundDtos, pageable, paginatedResponse, description);
        final PaginatedResponse<ProductDto> responseDto =
                PaginationUtils.mapToPaginatedResponse(foundDtos, paginatedResponse);

        return ResponseEntity.ok(responseDto);
    }

    private static void configureHateaos(List<ProductDto> foundDtos, Pageable pageable, PaginatedResponse<Product> paginatedResponse, String description) {
        foundDtos.forEach(productDto -> {
            BasicHateaosSetupLinks.setupGetAll(productDto);
            BasicHateaosSetupLinks.setupGetById(productDto);
            BasicHateaosSetupLinks.setupGetByName(productDto);
            BasicHateaosSetupLinks.setupGetByStatus(productDto);
            BasicHateaosSetupLinks.setupCreate(productDto);
            BasicHateaosSetupLinks.setupUpdate(productDto);
            BasicHateaosSetupLinks.setupDelete(productDto);

            productDto.add(WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(GetProductsByDescriptionController.class)
                                    .getProductByDescription(description, pageable)
                    ).withSelfRel()
            );
        });

        if (!foundDtos.isEmpty()) {
            List<Link> paginationLinks = PaginationUtils.createPaginationHateaosLinks(
                    pageable,
                    paginatedResponse,
                    page -> WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(GetProductsByDescriptionController.class)
                                    .getProductByDescription(description, pageable)
                    ));
            foundDtos.getFirst().add(paginationLinks);
        }
    }

}
