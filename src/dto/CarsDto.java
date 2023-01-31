package dto;

//класс для отображения автомобилей на странице cars.jsp

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CarsDto {

    int id;
    String brandName;
    String modelName;
    String image;
}
