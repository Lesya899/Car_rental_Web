package dto;

//поля для отображения данных из БД в carDescription.jsp

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CarDescriptionDto {
    int id;
    String brandName;
    String modelName;
    String status;
    int rentalPrice;
    String image;
    int carYear;
}
