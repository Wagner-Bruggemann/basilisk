package com.namgrengaw.basilisk.application.product.adapters.input.hateaos;

import com.namgrengaw.basilisk.application.product.adapters.input.controller.*;
import com.namgrengaw.basilisk.application.product.adapters.input.dto.ProductDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public class BasicHateaosSetupLinks {

    public static final PageRequest DEFAULT_PAGE_REQUEST = PageRequest.of(0, 10, Sort.unsorted());

    public static void setupGetAll(ProductDto productDto) {
        productDto.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder
                                .methodOn(FindAllProductControlller .class)
                                .findAllProduct(DEFAULT_PAGE_REQUEST)
                ).withRel("all-products")
        );
    }

    public static void setupGetById(ProductDto productDto) {
        productDto.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder
                                .methodOn(GetProductByIdController .class)
                                .getProductById(productDto.getId())
                ).withRel("get-by-id")
        );
    }

    public static void setupGetByName(ProductDto productDto) {
        productDto.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder
                                .methodOn(GetProductByNameController .class)
                                .getProductByName(productDto.getName(), DEFAULT_PAGE_REQUEST)
                ).withRel("get-by-name")
        );
    }

    public static void setupGetByDescription(ProductDto productDto) {
        productDto.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder
                                .methodOn(GetProductsByDescriptionController.class)
                                .getProductByDescription(productDto.getName(), DEFAULT_PAGE_REQUEST)
                ).withRel("get-by-description")
        );
    }

    public static void setupGetByStatus(ProductDto productDto) {
        productDto.add(WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(GetProductsByStatusController.class)
                                .getProductByStatus(productDto.getStatus(), DEFAULT_PAGE_REQUEST)
                ).withRel("get-by-status")
        );
    }

    public static void setupCreate(ProductDto productDto) {
        productDto.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder
                                .methodOn(CreateProductController .class)
                                .createProduct(productDto)
                ).withRel("create-product")
        );
    }

    public static void setupUpdate(ProductDto productDto) {
        productDto.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder
                                .methodOn(UpdateProductController .class)
                                .updateProduct(productDto.getId(), productDto)
                ).withRel("update-product")
        );

    }

    public static void setupDelete(ProductDto productDto) {
        productDto.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder
                                .methodOn(DeleteProductByIdController .class)
                                .deleteProductById(productDto.getId())
                ).withRel("delete-product")
        );
    }

}
