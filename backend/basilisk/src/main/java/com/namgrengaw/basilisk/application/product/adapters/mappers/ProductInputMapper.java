package com.namgrengaw.basilisk.application.product.adapters.mappers;

import com.namgrengaw.basilisk.application.infrastructure.components.Description;
import com.namgrengaw.basilisk.application.infrastructure.components.Identifier;
import com.namgrengaw.basilisk.application.infrastructure.components.Name;
import com.namgrengaw.basilisk.application.infrastructure.components.Status;
import com.namgrengaw.basilisk.application.infrastructure.mappers.DtoMapper;
import com.namgrengaw.basilisk.application.product.adapters.input.dto.ProductDto;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class ProductInputMapper implements DtoMapper<Product, ProductDto> {

    private Identifier id;
    private Name name;
    private Description description;
    private Status status;

    @Override
    public ProductDto toDto(Product domain) {
        return new ProductDto(
                domain.getId(),
                domain.getName(),
                domain.getDescription(),
                domain.getStatus()
        );
    }

    @Override
    public Product toDomain(ProductDto productDto) {
        this.name = new Name(productDto.name());
        this.status = new Status(productDto.status());
        Product product = new Product(this.name, this.status);

        final boolean isIdentifierAvailable = nonNull(productDto.id());
        final boolean isDescriptionAvailable = nonNull(productDto.description());
        final boolean isIdentifierAndDescriptionAvailable = isIdentifierAvailable && isDescriptionAvailable;

        if(isIdentifierAvailable) product = createProductWithoutDescription(productDto);
        if(isDescriptionAvailable) product = createProductWithGeneratedId(productDto);
        if(isIdentifierAndDescriptionAvailable) product = createProductWithAllFields(productDto);

        cleanUpAuxiliaryFields();

        return product;
    }

    private Product createProductWithGeneratedId(ProductDto productDto) {
        this.description = new Description(productDto.description());
        return new Product(this.name, this.description, this.status);
    }

    private Product createProductWithoutDescription(ProductDto productDto) {
        this.id = new Identifier(productDto.id());
        return new Product(this.id, this.name, this.status);
    }

    private Product createProductWithAllFields(ProductDto productDto) {
        this.id = new Identifier(productDto.id());
        this.description = new Description(productDto.description());
        return new Product(this.id, this.name, this.description, this.status);
    }

    private void cleanUpAuxiliaryFields() {
        this.id = null;
        this.name = null;
        this.description = null;
        this.status = null;
    }

}
