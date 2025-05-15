package com.namgrengaw.basilisk.application.infrastructure.validators;

import com.namgrengaw.basilisk.application.infrastructure.exceptions.BusinessLogicException;

import java.util.Objects;

public class ObjectUtils {

    public static void evaluateNonNullObjects(Object object) {
        if(Objects.isNull(object))
            throw new BusinessLogicException("The field cannot be null");
    }

}
