package dto;

//Dto class for saving information from the car rental request to the rent table in the database

import entity.RequestStatus;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;


    @Value
    @Builder
    public class CarRentalDto {
        Integer id;
        LocalDate dateStart;
        LocalDate terminationCarRental;
        Integer carId;
        RequestStatus requestStatus;
        Integer userId;
        String passport;
        Integer drivingExperience;
        String message;
    }

