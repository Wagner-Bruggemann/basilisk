package com.namgrengaw.basilisk.infrastructure.components;

import ch.qos.logback.core.util.StringUtil;
import com.namgrengaw.basilisk.infrastructure.validators.StringUtils;

import java.util.Objects;

public class Description {

    private String description;

    public Description(String description) {
        this.description = sanitize(description);
    }

    private String sanitize(String description) {
        if(Objects.isNull(description))
            return StringUtils.EMPTY;
        return description;
    }

    public String getValue() {
        return this.description;
    }

    public void update(String description) {
        this.description = sanitize(description);
    }

}
