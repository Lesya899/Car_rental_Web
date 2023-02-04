package dao;

import entity.CarEntity;
import java.sql.SQLException;

import entity.Model;
import entity.RentalStatus;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import util.ConnectionManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CarDao implements Dao<Integer, CarEntity> {

    private static final CarDao INSTANCE_CAR_DAO = new CarDao();


    private static final String DELETE_SQL = """
            DELETE FROM car
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO car (brand_name, model_id, color, status_id,rental_price, image, car_year) 
            VALUES (?, ?, ?, ?, ?, ?, ?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE car
            SET brand_name = ?,
                model_id = ?,
                color = ?,
                status_id = ?,
                rental_price=?,
                image=?,
                car_year=?
            WHERE id = ?    
            """;


    private static final String FIND_ALL_SQL = """
            SELECT car.id,
                brand_name,
                model_id,
                color,
                status_id,
                rental_price,
                image,
                car_year,
                model_name,
                capacity,
                status
            FROM car
            JOIN model m 
            ON car.model_id = m.id
            JOIN rental_status rs
            ON car.status_id = rs.id
            """;


    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE car.id = ?
            """;

    public static final String FIND_ALL_BY_CAR_ID = """
            SELECT  car.id,             
            brand_name,
            model_id,
            color,
            status_id,
            rental_price,
            image,
            car_year,
            model_name,
            capacity,
            status
            FROM car 
            JOIN model m
            ON car.model_id = m.id
            JOIN rental_status rs
            ON car.status_id = rs.id                              
            WHERE car.id = ?
            """;




    @Override
    @SneakyThrows
    public boolean delete(Integer id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        }
    }


    @Override
    @SneakyThrows
    public CarEntity save(CarEntity carEntity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, carEntity.getBrandName());
            preparedStatement.setInt(2, carEntity.getModel().getId());
            preparedStatement.setString(3, carEntity.getColor());
            preparedStatement.setInt(4, carEntity.getStatus().getId());
            preparedStatement.setInt(5, carEntity.getRentalPrice());
            preparedStatement.setString(6, carEntity.getImage());
            preparedStatement.setInt(7, carEntity.getCarYear());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                carEntity.setId(generatedKeys.getInt("id"));
            }
            return carEntity;
        }
    }


    @Override
    @SneakyThrows
    public void update(CarEntity carEntity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, carEntity.getBrandName());
            preparedStatement.setInt(2, carEntity.getModel().getId());
            preparedStatement.setString(3, carEntity.getColor());
            preparedStatement.setInt(4, carEntity.getStatus().getId());
            preparedStatement.setInt(5, carEntity.getRentalPrice());
            preparedStatement.setString(6, carEntity.getImage());
            preparedStatement.setInt(7, carEntity.getCarYear());
             preparedStatement.setInt(8, carEntity.getId());
             preparedStatement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows
    public Optional<CarEntity> findById(Integer id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
             preparedStatement.setInt(1, id);
             ResultSet resultSet = preparedStatement.executeQuery();
             CarEntity carEntity = null;
             if (resultSet.next()) {
                 carEntity = buildCar(resultSet);
             }
             return Optional.ofNullable(carEntity);
        }
    }

    @SneakyThrows
    public List<CarEntity> findAllByCarId(Integer carId) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_BY_CAR_ID)) {
            preparedStatement.setObject(1, carId);
            var resultSet = preparedStatement.executeQuery();
            List<CarEntity> carEntity = new ArrayList<>();
            while (resultSet.next()) {
                carEntity.add(buildCar(resultSet));
            }
            return carEntity;
        }
    }

    @Override
    @SneakyThrows
    public List<CarEntity> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
             ResultSet resultSet = preparedStatement.executeQuery();
             List<CarEntity> carEntity = new ArrayList<>();
             while (resultSet.next()) {
                 carEntity.add(buildCar(resultSet));
             }
             return carEntity;
        }
    }

    private CarEntity buildCar(ResultSet resultSet) throws SQLException {
        Model model = new Model(
                resultSet.getInt("id"),
                resultSet.getString("model_name"),
                resultSet.getInt("capacity")
        );
        RentalStatus status = new RentalStatus(
                resultSet.getInt("id"),
                resultSet.getString("status")
        );
        return new CarEntity(
                resultSet.getInt("id"),
                resultSet.getString("brand_name"),
                model,
                resultSet.getString("color"),
                status,
                resultSet.getInt("rental_price"),
                resultSet.getString("image"),
                resultSet.getInt("car_year"));
    }

    public static CarDao getInstanceCarDao() {
        return INSTANCE_CAR_DAO;
    }
}
