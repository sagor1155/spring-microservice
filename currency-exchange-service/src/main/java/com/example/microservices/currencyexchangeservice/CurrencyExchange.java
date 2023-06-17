package com.example.microservices.currencyexchangeservice;

import java.math.BigDecimal;

public class CurrencyExchange {

    private Long id;
    private String from;
    private String to;
    private BigDecimal conversionMultiple;

    /**
     * USE CASE:
     * Used when multiple instances are running on different port
     * Assign port for a particular instance of 'currency-exchange' microservice
     * Receiver will know which microservice instance is returning the response
     * */
    private String environment;

    public CurrencyExchange() {
    }

    public CurrencyExchange(Long id, String from, String to, BigDecimal conversionMultiple) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }

    public void setConversionMultiple(BigDecimal conversionMultiple) {
        this.conversionMultiple = conversionMultiple;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    @Override
    public String toString() {
        return "CurrencyExchange{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", conversionMultiple=" + conversionMultiple +
                '}';
    }
}
