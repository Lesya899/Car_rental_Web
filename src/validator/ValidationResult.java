package validator;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

    @Getter
    private final List<Error> errors = new ArrayList<>();

    //добавление ошибки в наш список
    public void add(Error error) {
        this.errors.add(error);
    }

    //валидный результат или нет
    public boolean isValid() {
        return errors.isEmpty(); //т.е. если пустой список ошибок, то ValidationResult валидный
    }
}
