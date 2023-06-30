package com.example.microservices.currencyconversionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.currency-exchange-server}")
    private String currencyExchangeUrl;

    @Autowired
    private CurrencyExchangeProxy proxy;

    @GetMapping(value = "/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
            ) {

        HashMap<String, String> uriVariable = new HashMap<>();
        uriVariable.put("from", from);
        uriVariable.put("to", to);
        ResponseEntity<CurrencyConversion> responseEntity = restTemplate.getForEntity(
                currencyExchangeUrl + "/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class, uriVariable
        );

        CurrencyConversion currencyConversion = responseEntity.getBody();
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(
                quantity.multiply(currencyConversion.getConversionMultiple())
        );
        return currencyConversion;
    }

    @GetMapping(value = "/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ) {
        CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from, to);
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(
                quantity.multiply(currencyConversion.getConversionMultiple())
        );
        return currencyConversion;
    }


}
