package com.devlhse.springreactiveapi.model;

import java.util.Date;
import java.util.Objects;

public class CarEvents {

    public CarEvents(Car model, Date when) {
        this.model = model;
        this.when = when;
    }

    private Car model;
    private Date when;

    public Car getModel() {
        return model;
    }

    public void setModel(Car model) {
        this.model = model;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarEvents carEvents = (CarEvents) o;
        return Objects.equals(model, carEvents.model) &&
                Objects.equals(when, carEvents.when);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, when);
    }
}
