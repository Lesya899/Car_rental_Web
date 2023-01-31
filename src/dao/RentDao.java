package dao;

//класс для извлечения данных из таблицы rent БД

import entity.*;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import util.ConnectionManager;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class RentDao implements Dao<Integer, RentEntity>{


    private static final RentDao INSTANCE_RENT_DAO = new RentDao();
    


    private static final String DELETE = """
            DELETE FROM rent
            WHERE id = ?
            """;


    private static final String SAVE = """
            INSERT INTO rent (date_start, duration, car_id, request_status, user_id, passport, driving_experience)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

    private static final String UPDATE = """
            UPDATE rent
            SET date_start = ?,
                duration = ?,
                car_id = ?,
                request_status = ?,
                user_id = ?,
                passport = ?,
                driving_experience = ?
            WHERE id = ?  
            """;


    private static final String FIND_ALL = """
            SELECT id,
                date_start,
                duration,
                car_id,
                request_status,
                user_id,
                passport,
                driving_experience                           
            FROM rent
            """;


    private static final String FIND_BY_ID = FIND_ALL + """
            WHERE id = ?
            """;


    @Override
    @SneakyThrows
    public boolean delete(Integer id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
             preparedStatement.setInt(1, id);
             return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    @SneakyThrows
    public RentEntity save(RentEntity rentEntity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDate(1, Date.valueOf(rentEntity.getDateStart())); // чтобы преобразовать LocalDate в java.sql.Date , мы можем просто использовать метод valueOf()
            preparedStatement.setInt(2, rentEntity.getDuration());
            preparedStatement.setInt(3, rentEntity.getCarId());
            preparedStatement.setString(4, rentEntity.getRequestStatus().name());
            preparedStatement.setInt(5, rentEntity.getUserId());
            preparedStatement.setString(6, rentEntity.getPassport());
            preparedStatement.setInt(7, rentEntity.getDrivingExperience());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                rentEntity.setId(generatedKeys.getInt("id"));
            }
            return rentEntity;
        }
    }
    @Override
    @SneakyThrows
    public void update(RentEntity rentEntity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setDate(1, Date.valueOf(rentEntity.getDateStart()));
            preparedStatement.setInt(2, rentEntity.getDuration());
            preparedStatement.setInt(3, rentEntity.getCarId());
            preparedStatement.setString(4, rentEntity.getRequestStatus().name());
            preparedStatement.setInt(5, rentEntity.getUserId());
            preparedStatement.setString(6, rentEntity.getPassport());
            preparedStatement.setInt(7, rentEntity.getDrivingExperience());
            preparedStatement.setInt(8, rentEntity.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows
    public Optional<RentEntity> findById(Integer id) {
        try (var connection = ConnectionManager.get()) {
            return findById(id, connection);
        }
    }


    @SneakyThrows
    public Optional<RentEntity> findById(Integer id, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
             preparedStatement.setInt(1, id);
             ResultSet resultSet = preparedStatement.executeQuery();
             RentEntity rentEntity = null;
             if (resultSet.next()) {
                rentEntity = buildRent(resultSet);
            }
            return Optional.ofNullable(rentEntity);
        }
    }

    @Override
    @SneakyThrows
    public List<RentEntity> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<RentEntity> rentEntities = new ArrayList<>();
            while (resultSet.next()) {
                rentEntities.add(buildRent(resultSet));
            }
            return rentEntities;
        }
    }


    private RentEntity buildRent(ResultSet resultSet) throws SQLException {
        return RentEntity.builder()
                .id(resultSet.getObject("id", Integer.class))
                .dateStart(resultSet.getObject("date_start", LocalDate.class))
                .duration(resultSet.getObject("duration", Integer.class))
                .carId(resultSet.getObject("car_id", Integer.class))
                .requestStatus(RequestStatus.valueOf(resultSet.getObject("request_status", String.class)))
                .userId(resultSet.getObject("user_id", Integer.class))
                .passport(resultSet.getObject("passport", String.class))
                .drivingExperience(resultSet.getObject("driving_experience", Integer.class))
                .build();

    }

    public static RentDao getInstanceRentDao() {
        return INSTANCE_RENT_DAO;
    }
}

