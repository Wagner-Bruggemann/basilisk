package com.namgrengaw.basilisk.application.product.adapters.output.repository;

import com.namgrengaw.basilisk.application.product.adapters.output.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    @Query("SELECT p FROM ProductEntity p WHERE p.name ILIKE %:name%")
    Page<ProductEntity> findAllByName(String name, PageRequest pageRequest);

    @Query("SELECT p FROM ProductEntity p WHERE p.description ILIKE %:description%")
    Page<ProductEntity> findAllByDescription(String description, PageRequest pageRequest);

    @Query("SELECT p FROM ProductEntity p WHERE p.status = :isActive")
    Page<ProductEntity> findAllByStatus(boolean isActive, PageRequest pageRequests);
}
