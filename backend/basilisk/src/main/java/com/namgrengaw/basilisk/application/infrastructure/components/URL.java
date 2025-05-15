package com.namgrengaw.basilisk.application.infrastructure.components;

import com.namgrengaw.basilisk.application.infrastructure.exceptions.BusinessLogicException;
import com.namgrengaw.basilisk.application.infrastructure.validators.StringUtils;

public class URL {

    private String url;

    public URL(String url) {
        evaluate(url);
        this.url = url;
    }

    public void evaluate(String url) {
        if(StringUtils.isBlank(url))
            throw new BusinessLogicException("The URL cannot be null or empty!");
    }

    public String getValue() {
        return this.url;
    }

    public void update(String url) {
        evaluate(url);
        this.url = url;
    }


}
