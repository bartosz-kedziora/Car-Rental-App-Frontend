package com.kodilla.carrental.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VinApiDto {

    @JsonProperty("Results")
    private List<VinBodyDto> vinBodyDtoList;
}
