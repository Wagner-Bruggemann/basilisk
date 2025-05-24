package com.namgrengaw.basilisk.application.infrastructure.components.pagination;

import com.namgrengaw.basilisk.application.infrastructure.components.NaturalLongNumber;
import com.namgrengaw.basilisk.application.infrastructure.components.NaturalNumber;

import java.util.List;

public record PaginatedResponse<T>(
        List<T> content,
        NaturalNumber page,
        NaturalNumber size,
        NaturalLongNumber totalElements,
        NaturalNumber totalPages
) {
}
