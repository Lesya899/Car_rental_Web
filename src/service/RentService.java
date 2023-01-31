package service;

//отображает данные из таблицы rent

import dao.CarDao;
import dao.RentDao;
import dto.RentDto;
import entity.RequestStatus;
import lombok.NoArgsConstructor;
import java.util.List;
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
                        .duration(rentEntity.getDuration())
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
                        .build())
                .collect(toList());
    }



    public List<RentDto> findRequestsByUser(Integer userId) {
        List<RentDto> rentlDto = findAllRequests();
        return rentlDto.stream()
                .filter(requestsDto -> requestsDto.getUserId().equals(userId))
                .collect(toList());
    }




    public static RentService getInstance() {
        return INSTANCE;
    }
}
