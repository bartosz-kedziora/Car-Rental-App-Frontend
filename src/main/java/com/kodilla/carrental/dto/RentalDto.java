package com.kodilla.carrental.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalDto {
    private Long id;
    private LocalDate rentedFrom;
    private LocalDate rentedTo;
    private BigDecimal rentalCost;
    private Long carId;
    private String carBrand;
    private String carModel;
    private Long userId;
    private String userName;
    private String userLastName;
    private String userEmail;
    private int userPhoneNumber;
}
