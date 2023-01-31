package dto;

//для отображения из БД данных о пользователе


import entity.Role;
import lombok.Builder;
import lombok.Value;



@Value
@Builder
public class UserDto {
    int id;
    String firstName;
    String lastName;
    String phoneNumber;
    String email;
    Role role;
}
