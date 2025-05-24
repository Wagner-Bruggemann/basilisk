package com.namgrengaw.basilisk.application.infrastructure.components;

import com.namgrengaw.basilisk.application.infrastructure.exceptions.BusinessLogicException;

public class NaturalNumber {

    private int number;

    public NaturalNumber(int number) {
        guardFunction(number);
        this.number = number;
    }

    private static void guardFunction(int number) {
        if(number < 0) throw new BusinessLogicException("The number must be equal or above zero");
    }

    public int getValue() {
        return this.number;
    }

    public void update(int number) {
        guardFunction(number);
        this.number = number;
    }

}
