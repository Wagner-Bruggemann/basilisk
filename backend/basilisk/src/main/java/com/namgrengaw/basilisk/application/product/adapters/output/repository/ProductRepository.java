package com.namgrengaw.basilisk.application.product.adapters.output.repository;

import com.namgrengaw.basilisk.application.product.adapters.output.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
}
