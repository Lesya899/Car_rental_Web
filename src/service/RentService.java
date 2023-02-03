package service;

//отображает данные из таблицы rent

import dao.CarDao;
import dao.RentDao;
import dto.RentDto;
import entity.RentEntity;
import entity.RentalStatus;
import entity.RequestStatus;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class RentService {

    private static final RentService INSTANCE = new RentService();

    private final RentDao rentDao = RentDao.getInstanceRentDao();

    private final CarDao carDao = CarDao.getInstanceCarDao();



    public List<RentDto> findAllRequests() {
        return rentDao.findAll().stream()//класс RentDao извлекает данные из БД и возвращает List<RentEntity>
                .map(rentEntity -> RentDto.builder() //затем List<RentEntity> преобразуем в List<RentDto>
                        .id(rentEntity.getId())
                        .dateStart(rentEntity.getDateStart())
                        .terminationCarRental(rentEntity.getTerminationCarRental())
                        .carId(rentEntity.getCarId())
                        .description(
                                """
                               %s  %s
                             """.formatted(carDao.findById(rentEntity.getCarId()).orElseThrow().getBrandName(), carDao.findById(rentEntity.getCarId()).orElseThrow().getModel().getModelName())
                        )
                        .requestStatus(RequestStatus.valueOf(rentEntity.getRequestStatus().name()))
                        .userId(rentEntity.getUserId())
                        .passport(rentEntity.getPassport())
                        .drivingExperience(rentEntity.getDrivingExperience())
                        .message(rentEntity.getMessage())
                        .build())
                .collect(toList());
    }


    //находим запрос по id пользователя
    public List<RentDto> findRequestsByUser(Integer userId) {
        List<RentDto> rentlDto = findAllRequests();
        return rentlDto.stream()
                .filter(requestsDto -> requestsDto.getUserId().equals(userId))
                .collect(toList());
    }

    public void processTheRequest(RentDto rentDto) {
            String mess = "";
            RequestStatus requestStatus =  null;
            if ((carDao.findById(rentDto.getCarId()).orElseThrow().getStatus().getRentalStatus()).equals("rented") ||
                    (carDao.findById(rentDto.getCarId()).orElseThrow().getStatus().getRentalStatus()).equals("under repair")) {
                requestStatus = RequestStatus.REJECTED;
                mess = "Choose another car to rent. This vehicle is rented.";
            } else {
                requestStatus = RequestStatus.CONFIRMED;
                mess = "Car rental request is confirmed.";
            }
            Optional<RentEntity> requestRentCar = rentDao.findById(rentDto.getId());
            if (requestRentCar.isPresent()) {
                RentEntity requestUpdate = RentEntity.builder()
                        .id(rentDto.getId())
                        .dateStart(rentDto.getDateStart())
                        .terminationCarRental(rentDto.getTerminationCarRental())
                        .carId(rentDto.getCarId())
                        .requestStatus(requestStatus)
                        .userId(rentDto.getUserId())
                        .passport(rentDto.getPassport())
                        .drivingExperience(rentDto.getDrivingExperience())
                        .message(mess)
                        .build();
                rentDao.update(requestUpdate);
            }
        }

    public static RentService getInstance() {
        return INSTANCE;
    }
}
