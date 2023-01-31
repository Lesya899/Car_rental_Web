package mapper;

//для добавления данных из формы регистрации в таблицу users
//преобразование CreateUserDto в сущность т.е.в UserEntity

import dto.CreateUserDto;
import entity.Role;
import entity.UserEntity;
import lombok.NoArgsConstructor;
import static lombok.AccessLevel.PRIVATE;



@NoArgsConstructor(access = PRIVATE)
public class CreateUserMapper implements Mapper<CreateUserDto, UserEntity> {

    private static final CreateUserMapper INSTANCE = new CreateUserMapper();

    @Override
    public UserEntity mapFrom(CreateUserDto object) {
        return UserEntity.builder()
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .phoneNumber(object.getPhoneNumber())
                .email(object.getEmail())
                .password(object.getPassword())
                .role(Role.valueOf(object.getRole()))
                .build();
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}
