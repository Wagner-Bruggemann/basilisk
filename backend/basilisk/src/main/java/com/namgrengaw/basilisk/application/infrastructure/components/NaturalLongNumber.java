package com.namgrengaw.basilisk.application.infrastructure.components;

import com.namgrengaw.basilisk.application.infrastructure.exceptions.BusinessLogicException;

public class NaturalLongNumber {

    private long number;

    public NaturalLongNumber(long number) {
        guardFunction(number);
        this.number = number;
    }

    private static void guardFunction(long number) {
        if(number < 0L) throw new BusinessLogicException("The number must be equal or above zero");
    }

    public long getValue() {
        return this.number;
    }

    public void update(int number) {
        guardFunction(number);
        this.number = number;
    }

}
