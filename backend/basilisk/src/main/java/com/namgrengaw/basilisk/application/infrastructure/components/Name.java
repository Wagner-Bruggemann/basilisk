package com.namgrengaw.basilisk.application.infrastructure.components;

import com.namgrengaw.basilisk.application.infrastructure.exceptions.BusinessLogicException;

import static com.namgrengaw.basilisk.application.infrastructure.validators.StringUtils.isBlank;

public class Name {

    private String name;

    public Name(String name) {
        evaluate(name);
        this.name = name;
    }

    private void evaluate(String name) {
        if(isBlank(name))
            throw new BusinessLogicException("The name field cannot be null or empty!");
    }

    public String getValue() {
        return this.name;
    }

    public void update(String name) {
        evaluate(name);
        this.name = name;
    }

}