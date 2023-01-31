package service;

//класс для добавления данных из заполненной пользователем формы на аренду автомобиля в БД


import dao.CarRentalRequestDao;
import dto.CarRentalDto;
import dto.RentDto;
import entity.CarRentalEntity;
import exception.ValidationException;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import mapper.CreateRentalMapper;
import validator.CreateRentalValidator;

import static lombok.AccessLevel.PRIVATE;


@NoArgsConstructor(access = PRIVATE)
public class CarRentalRequestService {

    private static final CarRentalRequestService INSTANCE = new CarRentalRequestService();

    private final CarRentalRequestDao carRentalRequestDao = CarRentalRequestDao.getInstance();
    private final CreateRentalValidator createRentalValidator = CreateRentalValidator.getInstance();

    private final CreateRentalMapper createRentalMapper = CreateRentalMapper.getInstance();



    @SneakyThrows
    public CarRentalEntity create(CarRentalDto carRentalDto) {
        var validationResult = createRentalValidator.isValid(carRentalDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var createRentRequest = createRentalMapper.mapFrom(carRentalDto);//преобразуем Dto в entity
        carRentalRequestDao.save(createRentRequest);
        return createRentRequest;
    }



    public static CarRentalRequestService getInstance() {
        return INSTANCE;
    }
}

