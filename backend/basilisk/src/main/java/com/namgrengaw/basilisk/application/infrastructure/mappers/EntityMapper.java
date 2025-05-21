package com.namgrengaw.basilisk.application.infrastructure.mappers;

public interface EntityMapper<T, E> {

    T toDomain(E entity);

    E toEntity(T entity);

}
