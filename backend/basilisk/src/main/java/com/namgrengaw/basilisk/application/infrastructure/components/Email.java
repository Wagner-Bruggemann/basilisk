package com.namgrengaw.basilisk.infrastructure.components;

import com.namgrengaw.basilisk.infrastructure.exceptions.BusinessLogicException;
import io.micrometer.common.util.StringUtils;

public class Email {

    private static final String EMAIL_REGEX = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
    private String email;

    public Email(String email) {
        evaluate(email);
        this.email = email;
    }

    private void evaluate(String email) {
        if(StringUtils.isBlank(email))
            throw new BusinessLogicException("The email cannot be null!");
        if(!email.matches(EMAIL_REGEX))
            throw new BusinessLogicException("The email value is not valid!");
    }

    public String getValue() {
        return this.email;
    }

    public void update(String email) {
        evaluate(email);
        this.email = email;
    }

}
