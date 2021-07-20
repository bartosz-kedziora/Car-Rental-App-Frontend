package com.kodilla.carrental.client;

import com.kodilla.carrental.config.BackEndConfiguration;
import com.kodilla.carrental.dto.UserDto;
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

@Component
@RequiredArgsConstructor
public class UserClient {

    private final RestTemplate restTemplate;
    private final BackEndConfiguration backEndConfiguration;

    public List<UserDto> getAllUsers() {
        try {
            URI url = UriComponentsBuilder.fromHttpUrl(backEndConfiguration.getUserEndpoint())
                    .build().encode().toUri();
            UserDto[] response = restTemplate.getForObject(url, UserDto[].class);
            return Arrays.asList(ofNullable(response).orElse(new UserDto[0]));
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }

    public void registerUser(UserDto userDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(backEndConfiguration.getUserEndpoint())
                .build().encode().toUri();
        restTemplate.postForObject(url, userDto, UserDto.class);
    }

    public UserDto getUserByEmail(String email) {
        try {
            URI url = UriComponentsBuilder.fromHttpUrl(backEndConfiguration.getUserEndpoint() + "/by_email/" + email)
                    .build().encode().toUri();
            UserDto response = restTemplate.getForObject(url, UserDto.class);
            return ofNullable(response).orElse(new UserDto());
        } catch (RestClientException e) {
            return new UserDto();
        }
    }

    public void updateUser(UserDto userDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(backEndConfiguration.getUserEndpoint()).build().encode().toUri();
        restTemplate.put(url, userDto);
    }

    public void deleteUser(Long id) {
        URI url = UriComponentsBuilder.fromHttpUrl(backEndConfiguration.getUserEndpoint() + "/" + id)
                .build().encode().toUri();
        restTemplate.delete(url);
    }
}
