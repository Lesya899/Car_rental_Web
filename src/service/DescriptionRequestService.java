package service;


import dao.CarDao;
import dao.RentDao;
import dto.RentDto;
import entity.RequestStatus;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class DescriptionRequestService {

    private static final DescriptionRequestService INSTANCE = new DescriptionRequestService();

    private final RentDao rentDao = RentDao.getInstanceRentDao();

    private final CarDao carDao = CarDao.getInstanceCarDao();

    public Optional<RentDto> findRequestById(Integer id) {
        return rentDao.findById(id)
                .map(requestsEntity -> RentDto.builder()
                        .id(requestsEntity.getId())
                        .dateStart(requestsEntity.getDateStart())
                        .duration(requestsEntity.getDuration())
                        .carId(requestsEntity.getCarId())
                        .description(
                                """
                                    %s  %s
                             """.formatted(carDao.findById(requestsEntity.getCarId()).orElseThrow().getBrandName(), carDao.findById(requestsEntity.getCarId()).orElseThrow().getModel().getModelName())
                        )
                        .requestStatus(RequestStatus.valueOf(requestsEntity.getRequestStatus().name()))
                        .userId(requestsEntity.getUserId())
                        .passport(requestsEntity.getPassport())
                        .drivingExperience(requestsEntity.getDrivingExperience())
                        .build());
    }

    public static DescriptionRequestService getInstance() {
        return INSTANCE;
    }

}
