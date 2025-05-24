package com.namgrengaw.basilisk.application.infrastructure.util.pagination;

import com.namgrengaw.basilisk.application.infrastructure.components.Name;
import com.namgrengaw.basilisk.application.infrastructure.components.NaturalLongNumber;
import com.namgrengaw.basilisk.application.infrastructure.components.NaturalNumber;
import com.namgrengaw.basilisk.application.infrastructure.components.pagination.PaginatedResponse;
import com.namgrengaw.basilisk.application.infrastructure.components.pagination.PaginationRequest;
import com.namgrengaw.basilisk.application.infrastructure.components.pagination.SortCriteria;
import com.namgrengaw.basilisk.application.infrastructure.components.pagination.SortDirection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class PaginationUtils {

    public static PaginationRequest mapToPaginationRequest(Pageable pageable) {
        List<SortCriteria> sortCriteria = pageable.getSort().stream()
                .map(order -> new SortCriteria(
                        new Name(order.getProperty()),
                        order.getDirection().isAscending() ? SortDirection.ASC : SortDirection.DESC
                ))
                .toList();
        return new PaginationRequest(
                new NaturalNumber(pageable.getPageNumber()),
                new NaturalNumber(pageable.getPageSize()),
                sortCriteria);
    }

    public static PageRequest mapToPageRequest(PaginationRequest pagination) {
        List<Sort.Order> orders = pagination
                .sortCriteria().stream()
                .map(criteria -> new Sort.Order(
                        criteria.direction() == SortDirection.ASC ? Sort.Direction.ASC : Sort.Direction.DESC,
                        criteria.field().getValue()))
                .toList();
        return PageRequest.of(
                pagination.page().getValue(),
                pagination.size().getValue(),
                Sort.by(orders));
    }

    public static <T, E> PaginatedResponse<T> mapToPaginatedResponse(List<T> domains, Page<E> pageResult) {
        return new PaginatedResponse<>(
                domains,
                new NaturalNumber(pageResult.getNumber()),
                new NaturalNumber(pageResult.getSize()),
                new NaturalLongNumber(pageResult.getTotalElements()),
                new NaturalNumber(pageResult.getTotalPages())
        );
    }

    public static <T, DTO> PaginatedResponse<DTO> mapToPaginatedResponse(List<DTO> dtos, PaginatedResponse<T> paginatedResponse) {
        return new PaginatedResponse<>(
                dtos,
                new NaturalNumber(paginatedResponse.page().getValue()),
                new NaturalNumber(paginatedResponse.size().getValue()),
                new NaturalLongNumber(paginatedResponse.totalElements().getValue()),
                new NaturalNumber(paginatedResponse.totalPages().getValue())
        );
    }

    public static <T> List<Link> createPaginationHateaosLinks(
            Pageable pageable,
            PaginatedResponse<T> paginatedResponse,
            Function<Pageable, WebMvcLinkBuilder> linkBuilder) {
        List<Link> links = new ArrayList<>();
        links.add(linkBuilder.apply(pageable).withSelfRel());
        links.add(linkBuilder.apply(
                PageRequest.of(0,
                        pageable.getPageSize(),
                        pageable.getSort())).withRel("first"));

        if (paginatedResponse.page().getValue() > 0)
            links.add(linkBuilder.apply(PageRequest.of(
                    paginatedResponse.page().getValue() - 1,
                    pageable.getPageSize(),
                    pageable.getSort())).withRel("prev"));

        if (paginatedResponse.page().getValue() < paginatedResponse.totalPages().getValue() - 1)
            links.add(linkBuilder.apply(PageRequest.of(
                    paginatedResponse.page().getValue() + 1,
                    pageable.getPageSize(),
                    pageable.getSort())).withRel("next"));

        links.add(linkBuilder.apply(PageRequest.of(
                paginatedResponse.totalPages().getValue() - 1,
                pageable.getPageSize(),
                pageable.getSort())).withRel("last"));

        return links;
    }

}
