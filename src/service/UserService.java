package service;

import dao.UserDao;
import dto.CreateUserDto;
import dto.UserDto;
import exception.ValidationException;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import mapper.CreateUserMapper;
import mapper.UserMapper;
import validator.CreateUserValidator;
import java.util.Optional;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();


    public Optional<UserDto> login(String email, String password, String role) {
        return userDao.findByEmailAndPassword(email, password, role)
                .map(userMapper::mapFrom);
    }

    @SneakyThrows
    public Integer create(CreateUserDto userDto) {
        var validationResult = createUserValidator.isValid(userDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var userEntity = createUserMapper.mapFrom(userDto);
        userDao.save(userEntity);
        return userEntity.getId();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
