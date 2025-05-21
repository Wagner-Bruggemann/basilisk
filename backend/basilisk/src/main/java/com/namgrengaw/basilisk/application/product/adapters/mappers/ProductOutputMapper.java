package com.namgrengaw.basilisk.application.product.adapters.mappers;

import com.namgrengaw.basilisk.application.infrastructure.components.Description;
import com.namgrengaw.basilisk.application.infrastructure.components.Identifier;
import com.namgrengaw.basilisk.application.infrastructure.components.Name;
import com.namgrengaw.basilisk.application.infrastructure.components.Status;
import com.namgrengaw.basilisk.application.infrastructure.mappers.EntityMapper;
import com.namgrengaw.basilisk.application.product.adapters.output.entity.ProductEntity;
import com.namgrengaw.basilisk.application.product.core.domain.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductOutputMapper implements EntityMapper<Product, ProductEntity> {
    @Override
    public ProductEntity toEntity(Product entity) {
        return new ProductEntity(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getStatus()
        );
    }

    @Override
    public Product toDomain(ProductEntity entity) {
        final Identifier id = new Identifier(entity.getId());
        final Name name = new Name(entity.getName());
        final Description description = new Description(entity.getDescription());
        final Status status = new Status(entity.getStatus());
        return new Product(id, name, description, status);
    }
}
