package com.kodilla.carrental.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class BackendConfiguration {
    @Value("${car.api.endpoint}")
    private String carEndpoint;
}
