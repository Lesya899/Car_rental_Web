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
                .map(carEntity -> CarsDto.builder()
                        .id(carEntity.getId())
                        .brandName(carEntity.getBrandName())
                        .modelName(carEntity.getModel().getModelName())
                        .color(carEntity.getColor())
                        .status(carEntity.getStatus().getRentalStatus())
                        .rentalPrice(carEntity.getRentalPrice())
                        .image(carEntity.getImage())
                        .carYear(carEntity.getCarYear())
                        .build())
                .collect(toList());
    }

    public List<CarsDto> findById(Integer id) {
        return carDao.findAllByCarId(id).stream()
                .map(carEntity -> CarsDto.builder()
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
