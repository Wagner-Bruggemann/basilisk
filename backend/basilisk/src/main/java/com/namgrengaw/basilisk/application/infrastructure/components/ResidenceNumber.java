package com.namgrengaw.basilisk.infrastructure.components;

import com.namgrengaw.basilisk.infrastructure.exceptions.BusinessLogicException;
import io.micrometer.common.util.StringUtils;

public class ResidenceNumber {

    private static final String NA = "N/A";
    private String number;

    public ResidenceNumber(String number) {
        evaluate(number);
        this.number = number;
    }

    public void evaluate(String number) {
        if(StringUtils.isBlank(number))
            throw new BusinessLogicException("The number cannot be null, empty or blank");
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            if(NA.equals(number))
                throw new BusinessLogicException("The number must be a integer or N/A");
        }
    }

    public void update(String number) {
        evaluate(number);
        this.number = number;
    }

    public String getValue() {
        return this.number;
    }

}
