package validator;

import dto.CarRentalDto;
import dto.RentDto;
import util.LocalDateFormatter;


public class CreateRentalValidator implements Validator<CarRentalDto> {

    private static final CreateRentalValidator INSTANCE = new CreateRentalValidator();

    @Override
    public ValidationResult isValid(CarRentalDto object) {
        var validResult = new ValidationResult();

        if (!LocalDateFormatter.isValid(object.getDateStart().toString())) {
            validResult.add(Error.of("invalid.dateStart", "Date start is invalid"));
        }
        if (String.valueOf(object.getDuration()).isEmpty()) {
            validResult.add(Error.of("invalid.duration", "Duration is invalid"));
        }
        return validResult;
    }

    public static CreateRentalValidator getInstance() {
        return INSTANCE;
    }
}

