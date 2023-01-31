package service;

import dao.CarDao;
import dto.CarsDto;
import lombok.NoArgsConstructor;

import java.util.List;


import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CarService {

    private static final CarService INSTANCE = new CarService();

    private final CarDao carDao = CarDao.getInstanceCarDao();




    public List<CarsDto> findAllCar() {
        return carDao.findAll().stream()
                .map(car -> CarsDto.builder()
                        .id(car.getId())
                        .brandName(car.getBrandName())
                        .modelName(car.getModel().getModelName())
                        .image(car.getImage())
                        .build()
                )
                .collect(toList());
    }

    public Integer findCarId(List<CarsDto> cars, String modelName) {
        return cars.stream()
                .filter(carDto -> carDto.getModelName().equals(modelName))
                .mapToInt(CarsDto::getId)
                .sum();
    }


    public static CarService getInstance() {
        return INSTANCE;
    }
}
