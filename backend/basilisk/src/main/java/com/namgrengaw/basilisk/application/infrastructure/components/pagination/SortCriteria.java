package com.namgrengaw.basilisk.application.infrastructure.components.pagination;

import com.namgrengaw.basilisk.application.infrastructure.components.Name;

public record SortCriteria(Name field, SortDirection direction) {
}
