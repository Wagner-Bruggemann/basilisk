package com.namgrengaw.basilisk.infrastructure.components;

import com.namgrengaw.basilisk.infrastructure.exceptions.BusinessLogicException;

import static com.namgrengaw.basilisk.infrastructure.validators.StringUtils.isBlank;

public class Path {

    private String path;

    public Path(String path) {
        evaluate(path);
        this.path = path;
    }

    public void evaluate(String path) {
        if(isBlank(path))
            throw new BusinessLogicException("The path field cannot be null");
    }

    public void update(String path) {
        evaluate(path);
        this.path = path;
    }

    public String getValue() {
        return this.path;
    }

}
