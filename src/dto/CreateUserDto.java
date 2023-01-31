package dto;

//для добавления данных из формы регистрации (registration.jsp) в БД

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserDto {
    String firstName;
    String lastName;
    String phoneNumber;
    String email;
    String password;
    String role;
}