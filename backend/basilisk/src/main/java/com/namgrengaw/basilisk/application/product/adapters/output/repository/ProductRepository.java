package com.namgrengaw.basilisk.application.product.adapters.output.repository;

import com.namgrengaw.basilisk.application.product.adapters.output.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    @Query("SELECT p FROM ProductEntity p WHERE p.name ILIKE %:name%")
    Optional<List<ProductEntity>> findAllByName(String name);

    @Query("SELECT p FROM ProductEntity p WHERE p.description ILIKE %:description%")
    Optional<List<ProductEntity>> findAllByDescription(String description);

    @Query("SELECT p FROM ProductEntity p WHERE p.status = :isActive")
    Optional<List<ProductEntity>> findAllByStatus(boolean isActive);
}
