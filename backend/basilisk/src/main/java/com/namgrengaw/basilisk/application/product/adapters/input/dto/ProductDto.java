package com.namgrengaw.basilisk.application.product.adapters.input.dto;

import java.io.Serial;
import java.io.Serializable;

public record ProductDto(String id, String name, String description, boolean status) implements Serializable {

    @Serial
    private static final long serialVersionUID = 321491236564L;

}
