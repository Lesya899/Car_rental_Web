package entity;


//для извлечения данных из таблицы rent

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentEntity {

        private Integer id;
        private LocalDate dateStart;
        private Integer duration;
        private Integer carId;
        private RequestStatus requestStatus;
        private Integer userId;
        private String passport;
        private Integer drivingExperience;
}
