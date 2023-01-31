package dto;

//класс Dto для добавления данных из заявления на аренду в БД

import entity.RequestStatus;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;


    @Value
    @Builder
    public class CarRentalDto {
        Integer id;
        LocalDate dateStart;
        Integer duration;
        Integer carId;
        RequestStatus requestStatus;
        Integer userId;
        String passport;
        Integer drivingExperience;
    }

