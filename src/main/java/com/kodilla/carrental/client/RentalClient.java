package com.kodilla.carrental.client;

import com.kodilla.carrental.config.BackEndConfiguration;
import com.kodilla.carrental.dto.RentalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@Component
public class RentalClient {

    private final RestTemplate restTemplate;
    private final BackEndConfiguration backEndConfiguration;

    public List<RentalDto> getAllRentals() {
        try {
            URI url = UriComponentsBuilder.fromHttpUrl(backEndConfiguration.getRentalEndpoint()).build().encode().toUri();
            RentalDto[] response = restTemplate.getForObject(url, RentalDto[].class);
            return Arrays.asList(ofNullable(response).orElse(new RentalDto[0]));
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }

    public List<RentalDto> getRentalsByUserId(Long userId) {
        try {
            URI url = UriComponentsBuilder.fromHttpUrl(backEndConfiguration.getRentalEndpoint() + "/by_user_id/" + userId)
                    .build().encode().toUri();
            RentalDto[] response = restTemplate.getForObject(url, RentalDto[].class);
            return Arrays.asList(ofNullable(response).orElse(new RentalDto[0]));
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }

    public void createRental(RentalDto rentalDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(backEndConfiguration.getRentalEndpoint()).build().encode().toUri();
        restTemplate.postForObject(url, rentalDto, RentalDto.class);
    }

    public void updateRental(RentalDto rentalDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(backEndConfiguration.getRentalEndpoint()).build().encode().toUri();
        restTemplate.put(url, rentalDto);
    }

    public void deleteRental(Long id) {
        URI url = UriComponentsBuilder.fromHttpUrl(backEndConfiguration.getRentalEndpoint() + "/" + id)
                .build().encode().toUri();
        restTemplate.delete(url);
    }
}
