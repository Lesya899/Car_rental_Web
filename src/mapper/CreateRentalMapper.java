package mapper;



import dto.CarRentalDto;
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
                .terminationCarRental(Date.valueOf(object.getTerminationCarRental()).toLocalDate())
                .carId(object.getCarId())
                .requestStatus(object.getRequestStatus())
                .userId(object.getUserId())
                .passport(object.getPassport())
                .drivingExperience(object.getDrivingExperience())
                .message(object.getMessage())
                .build();
    }


    public static CreateRentalMapper getInstance() {
        return INSTANCE;
    }
}
