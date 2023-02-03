package entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarEntity {

    private Integer id;
    private String brandName;
    private Model model;
    private String color;
    private RentalStatus status;
    private Integer rentalPrice;
    private String image;
    private Integer carYear;


}
