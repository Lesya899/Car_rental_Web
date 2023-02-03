package dto;

//Dto class to get information about cars from the car table

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CarsDto {
    Integer id;
    String brandName;
    String modelName;
    String color;
    String status;
    Integer rentalPrice;
    String image;
    Integer carYear;
}
