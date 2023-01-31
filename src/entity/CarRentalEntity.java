package entity;

//данные,полученные из заявки на аренду автомобиля

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarRentalEntity {
    private int id;
    private LocalDate dateStart;
    private int duration;
    private int carId;
    private RequestStatus requestStatus;
    private int userId;
    private String passport;
    private int drivingExperience;
}
