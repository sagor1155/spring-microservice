package com.example.microservices.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private LimitsConfiguration configuration;

    @GetMapping(value = "/limits")
    public Limits getLimits() {
        return new Limits(configuration.getMinimum(), configuration.getMaximum());
    }

}
