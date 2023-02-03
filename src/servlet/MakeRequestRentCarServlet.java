package servlet;



import dto.CarRentalDto;
import dto.CarsDto;
import dto.UserDto;
import entity.RequestStatus;
import exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CarRentalRequestService;
import service.CarService;
import util.JspHelper;


import java.io.IOException;
import java.sql.Date;
import java.util.List;

import static util.UrlPath.*;

@WebServlet(MAKE_REQUEST_TO_RENT_CAR)
public class MakeRequestRentCarServlet extends HttpServlet {

    private final CarRentalRequestService carRentalRequestService = CarRentalRequestService.getInstance();
    private final CarService carService = CarService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("carId"));
        req.setAttribute("car", carService.findById(id));
        req.getRequestDispatcher(JspHelper.getPath("makeRequestToRentCar"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CarsDto> cars = carService.findAllCar();
        UserDto user = (UserDto) req.getSession().getAttribute("user");
        var carRentalDto = CarRentalDto.builder()
                .dateStart(Date.valueOf(req.getParameter("dataStart")).toLocalDate())
                .terminationCarRental(Date.valueOf(req.getParameter("terminationCarRental")).toLocalDate())
                .carId(carService.findCarId(cars, req.getParameter("model")))
                .requestStatus(RequestStatus.PROCESSING)
                .userId(user.getId())
                .passport(req.getParameter("passport"))
                .drivingExperience(Integer.parseInt(req.getParameter("drivingExperience")))
                .message("Car rental request is in processing.")
                .build();

        try {
            carRentalRequestService.create(carRentalDto);
            resp.sendRedirect(PAGE_USER);
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}
