package com.namgrengaw.basilisk.infrastructure.components;

import com.namgrengaw.basilisk.infrastructure.exceptions.BusinessLogicException;

import java.util.UUID;

import static com.namgrengaw.basilisk.infrastructure.validators.StringUtils.isBlank;

public class Identifier {

    private final String uuid;

    public Identifier(String uuid) {
        evaluate(uuid);
        this.uuid = uuid;
    }

    public Identifier() {
        this.uuid = generateNewIdentifier();
    }

    private String generateNewIdentifier() {
        return UUID.randomUUID().toString();
    }

    private void evaluate(String uuid) {
        if(isBlank(uuid))
            throw new BusinessLogicException("The identifier field cannot be null or empty!");
    }

    public String getValue() {
        return this.uuid;
    }

}