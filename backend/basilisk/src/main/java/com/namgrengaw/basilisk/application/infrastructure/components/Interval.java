package com.namgrengaw.basilisk.application.infrastructure.components;

import com.namgrengaw.basilisk.application.infrastructure.exceptions.BusinessLogicException;
import com.namgrengaw.basilisk.application.infrastructure.util.time.TimeUtils;

import java.util.Date;

public class Interval {

    private Date initialDate;
    private Date finalDate;
    private long interval;

    public Interval() {
        this.initialDate = new Date();
        this.finalDate = new Date();
    }

    public Interval(Date initialDate, Date finalDate) {
        evaluate(initialDate, finalDate);
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        calculateInterval();
    }

    public void updateInitialDate(Date initialDate) {
        this.initialDate = initialDate;
        evaluate();
    }

    public void updateFinalDate(Date finalDate) {
        this.finalDate = finalDate;
        evaluate();
        calculateInterval();
    }

    private void evaluate() {
        evaluate(this.initialDate, this.finalDate);
    }

    private void evaluate(Date initialDate, Date finalDate) {
        if(initialDate.after(finalDate))
            throw new BusinessLogicException("The final date cannot be before the initial date");
        if(initialDate.equals(finalDate))
            throw new BusinessLogicException("The final date cannot be the same as the initial date");
    }

    private void calculateInterval() {
        long initialSeconds = this.initialDate.toInstant().getEpochSecond();
        long finalSeconds = this.finalDate.toInstant().getEpochSecond();
        this.interval = finalSeconds - initialSeconds;
    }
    
    public String getFormatedDate() {
        return TimeUtils.formatedDate(this.interval);
    }

    public long getValue() {
        return this.interval;
    }

}
