package dao;

//класс DAO для добавления данных из заявки на аренду в БД

import entity.CarRentalEntity;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import util.ConnectionManager;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CarRentalRequestDao implements Dao<Integer, CarRentalEntity>{


    private static final CarRentalRequestDao INSTANCE = new CarRentalRequestDao();

    private static final String SAVE = """
            INSERT INTO rent (date_start, duration, car_id, request_status, user_id, passport,driving_experience)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;


    @Override
    @SneakyThrows
    public CarRentalEntity save(CarRentalEntity entity) {
        try (Connection connection = ConnectionManager.get();
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDate(1, Date.valueOf(entity.getDateStart())); // чтобы преобразовать LocalDate в java.sql.Date , мы можем просто использовать метод valueOf()
            preparedStatement.setInt(2, entity.getDuration());
            preparedStatement.setInt(3, entity.getCarId());
            preparedStatement.setString(4, entity.getRequestStatus().name());
            preparedStatement.setInt(5, entity.getUserId());
            preparedStatement.setString(6, entity.getPassport());
            preparedStatement.setInt(7, entity.getDrivingExperience());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt("id"));
            }
            return entity;
        }
    }

    @Override
    public void update(CarRentalEntity entity) {
    }

    @Override
    public Optional<CarRentalEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<CarRentalEntity> findAll() {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    public static CarRentalRequestDao getInstance() {
        return INSTANCE;
    }
}
