package mapper;

//класс для добавления данных из заполненной пользователем формы на аренду автомобиля в БД


import dto.CarRentalDto;
import dto.RentDto;
import entity.*;
import lombok.NoArgsConstructor;

import java.sql.Date;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateRentalMapper implements Mapper<CarRentalDto, CarRentalEntity> {


    private static final CreateRentalMapper INSTANCE = new CreateRentalMapper();

    @Override
    public CarRentalEntity mapFrom(CarRentalDto object) {
        return CarRentalEntity.builder()
                .dateStart(Date.valueOf(object.getDateStart()).toLocalDate())
                .duration(object.getDuration())
                .carId(object.getCarId())
                .requestStatus(object.getRequestStatus())
                .userId(object.getUserId())
                .passport(object.getPassport())
                .drivingExperience(object.getDrivingExperience())
                .build();
    }


    public static CreateRentalMapper getInstance() {
        return INSTANCE;
    }
}
