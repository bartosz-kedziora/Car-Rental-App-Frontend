package com.kodilla.carrental.client;

import com.kodilla.carrental.config.BackEndConfiguration;
import com.kodilla.carrental.dto.VinApiDto;
import com.kodilla.carrental.dto.VinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class VinApiClient {

    private final RestTemplate restTemplate;
    private final BackEndConfiguration backEndConfiguration;

    public VinApiDto decodeVinNumber(VinDto vinDto) {
        try {
            URI url = UriComponentsBuilder.fromHttpUrl(backEndConfiguration.getVinApiEndpoint() + "/" + vinDto.getVinNumber())
                    .build().encode().toUri();
            VinApiDto response = restTemplate.getForObject(url, VinApiDto.class);
            return ofNullable(response).orElse(new VinApiDto());
        } catch (RestClientException e) {
            return new VinApiDto();
        }
    }
}
