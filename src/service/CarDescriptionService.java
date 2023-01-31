package service;

import dao.CarDao;
import dto.CarDescriptionDto;
import lombok.NoArgsConstructor;


import java.util.List;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CarDescriptionService {
    private static final CarDescriptionService INSTANCE = new CarDescriptionService();

    private final CarDao carDao = CarDao.getInstanceCarDao();




    public List<CarDescriptionDto> findById(Integer id) {
        return carDao.findAllByCarId(id).stream()
                .map(carEntity -> CarDescriptionDto.builder()
                        .id(carEntity.getId())
                        .brandName(carEntity.getBrandName())
                        .modelName(carEntity.getModel().getModelName())
                        .status(carEntity.getStatus().getRentalStatus())
                        .rentalPrice(carEntity.getRentalPrice())
                        .image(carEntity.getImage())
                        .carYear(carEntity.getCarYear())
                        .build())
                .collect(toList());
    }




    public static CarDescriptionService getInstance() {
        return INSTANCE;
    }
}

