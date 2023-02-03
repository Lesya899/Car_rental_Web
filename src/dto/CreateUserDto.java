package dto;

//Class Dto to add data from the registration form to the table users

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