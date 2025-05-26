package com.namgrengaw.basilisk.application.product.adapters.input.controller;

import com.namgrengaw.basilisk.application.infrastructure.components.pagination.PaginatedResponse;
import com.namgrengaw.basilisk.application.infrastructure.components.pagination.PaginationRequest;
import com.namgrengaw.basilisk.application.infrastructure.mappers.DtoMapper;
import com.namgrengaw.basilisk.application.infrastructure.util.pagination.PaginationUtils;
import com.namgrengaw.basilisk.application.product.adapters.input.dto.ProductDto;
import com.namgrengaw.basilisk.application.product.adapters.input.hateaos.BasicHateaosSetupLinks;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import com.namgrengaw.basilisk.application.product.core.ports.input.FindAllProductsInputGateway;
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
    public ResponseEntity<PaginatedResponse<ProductDto>> findAllProduct(
            @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.ASC)
            @Parameter(hidden = true)
            Pageable pageable) {
        final PaginationRequest paginationRequest = PaginationUtils.mapToPaginationRequest(pageable);
        final PaginatedResponse<Product> paginatedResponse = findAllProductsInputGateway.findAllProducts(paginationRequest);

        final List<ProductDto> foundDtos = paginatedResponse.content().stream()
                .map(mapper::toDto)
                .toList();

        configureHateaos(foundDtos, pageable, paginatedResponse);

        final PaginatedResponse<ProductDto> responseDto =
                PaginationUtils.mapToPaginatedResponse(foundDtos, paginatedResponse);

        return ResponseEntity.ok(responseDto);
    }

    private static void configureHateaos(List<ProductDto> foundDtos, Pageable pageable, PaginatedResponse<Product> paginatedResponse) {
        foundDtos.forEach(dto -> {
            BasicHateaosSetupLinks.setupGetById(dto);
            BasicHateaosSetupLinks.setupGetByName(dto);
            BasicHateaosSetupLinks.setupGetByDescription(dto);
            BasicHateaosSetupLinks.setupGetByStatus(dto);
            BasicHateaosSetupLinks.setupCreate(dto);
            BasicHateaosSetupLinks.setupUpdate(dto);
            BasicHateaosSetupLinks.setupDelete(dto);

            dto.add(WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(FindAllProductControlller.class)
                                    .findAllProduct(pageable)
                    ).withSelfRel());
            });

        if (!foundDtos.isEmpty()) {
            List<Link> paginationLinks = PaginationUtils.createPaginationHateaosLinks(
                    pageable,
                    paginatedResponse,
                    page -> WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(FindAllProductControlller.class)
                                    .findAllProduct(page)
                    ));
            foundDtos.getFirst().add(paginationLinks);
        }

    }

}
