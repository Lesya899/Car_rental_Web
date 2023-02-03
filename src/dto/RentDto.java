package dto;

// Dto class to retrieve information from the database on car rental requests


import entity.RequestStatus;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;


@Value
@Builder
public class RentDto {
    Integer id;
    LocalDate dateStart;
    LocalDate terminationCarRental;
    Integer carId;
    String description;
    RequestStatus requestStatus;
    Integer userId;
    String passport;
    Integer drivingExperience;
    String message;
}
