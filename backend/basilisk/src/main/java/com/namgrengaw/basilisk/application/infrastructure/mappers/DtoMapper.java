package com.namgrengaw.basilisk.application.infrastructure.mappers;

public interface DtoMapper<T, DTO> {

    T toDomain(DTO dto);

    DTO toDto(T domain);

}
