package com.namgrengaw.basilisk.application.infrastructure.components.pagination;

import com.namgrengaw.basilisk.application.infrastructure.components.NaturalNumber;

import java.util.List;

public record PaginationRequest(NaturalNumber page, NaturalNumber size, List<SortCriteria> sortCriteria) {

}
