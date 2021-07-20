package com.kodilla.carrental.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeResultDto {

    @JsonProperty("title")
    private String title;

    @JsonProperty("position")
    private GeocodePositionDto position;
}
