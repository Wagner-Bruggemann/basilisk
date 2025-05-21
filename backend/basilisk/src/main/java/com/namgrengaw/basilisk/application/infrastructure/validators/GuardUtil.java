package com.namgrengaw.basilisk.application.infrastructure.validators;

import com.namgrengaw.basilisk.application.infrastructure.exceptions.BusinessLogicException;

import java.util.Objects;

public class GuardUtil {

    public static void required(Object object) {
        required(object, "The object is required!");
    }

    public static void required(Object object, String message) {
        if(Objects.isNull(object))
            throw new BusinessLogicException(message);
    }

}
