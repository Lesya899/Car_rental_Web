package entity;

/**
 * Класс предназначен:
 * -для добавления данных из формы регистрации в таблицу users;
 * -для сверки введенных логина и пароля с данными в таблицы users
 */



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private Role role;

}
