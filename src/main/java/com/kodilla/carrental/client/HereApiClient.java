package com.kodilla.carrental.client;

import com.kodilla.carrental.config.BackEndConfiguration;
import com.kodilla.carrental.dto.CarAgencyDto;
import com.kodilla.carrental.dto.CoordinatesDto;
import com.kodilla.carrental.dto.GeocodeDto;
import com.kodilla.carrental.dto.ZipCodeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class HereApiClient {

    private final RestTemplate restTemplate;
    private final BackEndConfiguration backEndConfiguration;

    public GeocodeDto getCoordinates(ZipCodeDto zipCodeDto) {
        try {
            URI url = UriComponentsBuilder.fromHttpUrl(backEndConfiguration.getHereApiEndpoint() +
                    "/check_coordinates_by_postal_code/" + zipCodeDto.getZipCode())
                    .build().encode().toUri();
            GeocodeDto response = restTemplate.getForObject(url, GeocodeDto.class);
            return ofNullable(response).orElse(new GeocodeDto());
        } catch (RestClientException e) {
            return new GeocodeDto();
        }
    }

    public CarAgencyDto searchCarRentalAgencies(CoordinatesDto coordinatesDto) {
        try {
            URI url = UriComponentsBuilder.fromHttpUrl(backEndConfiguration.getHereApiEndpoint() +
                    "/search_nearest_agencies_by_coordinates/")
                    .queryParam("latitude", coordinatesDto.getLatitude())
                    .queryParam("longitude", coordinatesDto.getLongitude())
                    .build().encode().toUri();
            CarAgencyDto response = restTemplate.getForObject(url, CarAgencyDto.class);
            return ofNullable(response).orElse(new CarAgencyDto());
        } catch (RestClientException e) {
            return new CarAgencyDto();
        }
    }
}
