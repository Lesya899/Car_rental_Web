package validator;

//принимает сущность и возвращает

public interface Validator<T> {

    ValidationResult isValid(T object);
}
