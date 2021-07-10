package com.kodilla.carrental.config;


import org.springframework.beans.factory.annotation.Value;

public class BackendConfiguration {
    @Value("${car.api.endpoint}")
    private String carEndpoint;
}
