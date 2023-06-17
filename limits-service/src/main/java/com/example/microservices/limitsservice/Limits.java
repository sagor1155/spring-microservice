package com.example.microservices.limitsservice;

public class Limits {
    private Integer minimum;
    private Integer maximum;

    public Limits() {
    }

    public Limits(Integer minimum, Integer maximum) {
        this.maximum = maximum;
        this.minimum = minimum;
    }

    public Integer getMaximum() {
        return maximum;
    }

    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
    }

    public Integer getMinimum() {
        return minimum;
    }

    public void setMinimum(Integer minimum) {
        this.minimum = minimum;
    }

    @Override
    public String toString() {
        return "Limits{" +
                "maximum=" + maximum +
                ", minimum=" + minimum +
                '}';
    }
}
