package dto;

// для извлечения необходимых данных из БД


import entity.RequestStatus;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;


@Value
@Builder
public class RentDto {
    Integer id;
    LocalDate dateStart;
    Integer duration;
    Integer carId;
    String description;
    RequestStatus requestStatus;
    Integer userId;
    String passport;
    Integer drivingExperience;
}
