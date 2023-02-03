package dto;

//Dto class to retrieve information from the users table


import entity.Role;
import lombok.Builder;
import lombok.Value;


@Value
@Builder
public class UserDto {
    Integer id;
    String firstName;
    String lastName;
    String phoneNumber;
    String email;
    Role role;
}
