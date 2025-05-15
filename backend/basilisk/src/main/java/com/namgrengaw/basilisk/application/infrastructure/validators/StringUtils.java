package com.namgrengaw.basilisk.infrastructure.validators;

import java.util.Objects;

public class StringUtils {

    public static final String EMPTY = "";

    public static boolean isBlank(String reference) {
        return Objects.isNull(reference) || reference.isBlank() || reference.trim().isEmpty();
    }

}
