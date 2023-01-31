package servlet;

//После того как клиент успешно залогинился в системе, он переходит на страницу со списком автомобилей для аренды

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CarService;
import util.JspHelper;

import java.io.IOException;

import static util.UrlPath.CARS;

@WebServlet(CARS)
public class CarServlet extends HttpServlet {

    private final CarService carService = CarService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cars", carService.findAllCar());
        req.getRequestDispatcher(JspHelper.getPath("cars"))
                .forward(req, resp);
    }
}
