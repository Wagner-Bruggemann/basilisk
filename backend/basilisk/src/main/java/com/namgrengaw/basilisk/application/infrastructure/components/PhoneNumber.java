package com.namgrengaw.basilisk.infrastructure.components;

import com.namgrengaw.basilisk.infrastructure.exceptions.BusinessLogicException;
import io.micrometer.common.util.StringUtils;

public class PhoneNumber {

    private static final String PHONE_REGEX = "^\\([1-9]{2}\\) (?:[2-8]|9[0-9])[0-9]{3}-[0-9]{4}$";
    private String phoneNumber;

    public PhoneNumber(String phoneNumber) {
        evaluate(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    private void evaluate(String phoneNumber) {
        if(StringUtils.isBlank(phoneNumber))
            throw new BusinessLogicException("The phone number cannot be null!");
        if(!phoneNumber.matches(PHONE_REGEX))
            throw new BusinessLogicException("The phone number is not a valid number!");
    }

    public String getValue() {
        return this.phoneNumber;
    }

    public String getRawValue() {
        return this.phoneNumber
                .replace(",","")
                .replace("(", "")
                .replace(")", "")
                .replace("-", "")
                .replace(" ", "");
    }

    public void update(String phoneNumber) {
        evaluate(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

}
