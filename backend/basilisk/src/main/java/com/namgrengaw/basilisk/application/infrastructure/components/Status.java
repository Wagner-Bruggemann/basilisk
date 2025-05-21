package com.namgrengaw.basilisk.application.infrastructure.components;

import com.namgrengaw.basilisk.application.infrastructure.exceptions.BusinessLogicException;

import java.util.Objects;

public class Status {

    private Boolean status;

    public Status(boolean status) {
        evaluate(status);
        this.status = status;
    }

    private void evaluate(boolean status) {
        if(Objects.isNull(this.status))
            return;

        if(this.status == status) {
            throw new BusinessLogicException("The status of the object is already " + this.toString());
        }
    }

    public boolean isActive() {
        return this.status;
    }

    public void setStatus(boolean status) {
        evaluate(status);
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status ? "ACTIVE" : "INNACTIVE";
    }
}
