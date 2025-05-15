package com.namgrengaw.basilisk.application.infrastructure.components;

import com.namgrengaw.basilisk.application.infrastructure.exceptions.BusinessLogicException;

import java.math.BigDecimal;
import java.math.MathContext;

public class Monetary {

    private final MathContext context;
    private BigDecimal value;

    public Monetary(double value) {
        evaluate(value);
        this.value = BigDecimal.valueOf(value);
        this.context = MathContext.DECIMAL64;
    }

    public void evaluate(double value) {
        BigDecimal parsedValue = BigDecimal.valueOf(value);
        if(parsedValue.compareTo(BigDecimal.ZERO) < 0)
            throw new BusinessLogicException("The value parameter cannot be negative!");

        if(this.value.subtract(parsedValue).compareTo(BigDecimal.ZERO) < 0)
            throw new BusinessLogicException("Not enought balance to complete the operation!");

    }

    public void updateValue(double value) {
        this.value = this.value.add(BigDecimal.valueOf(value), context);
    }

    public double getValue() {
        return this.value.doubleValue();
    }

}
