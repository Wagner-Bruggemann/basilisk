package com.namgrengaw.basilisk.application.product.adapters.input.hateaos;

import com.namgrengaw.basilisk.application.product.adapters.input.controller.*;
import com.namgrengaw.basilisk.application.product.adapters.input.dto.ProductDto;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public class BasicHateaosSetupLinks {

    public static void setupGetAll(ProductDto productDto) {
        productDto.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder
                                .methodOn(FindAllProductControlller .class)
                                .findAllProduct()
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
