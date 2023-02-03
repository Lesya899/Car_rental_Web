package servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CarService;
import util.JspHelper;

import java.io.IOException;

import static util.UrlPath.LIST_CARS_FOR_ADMIN;


@WebServlet(LIST_CARS_FOR_ADMIN)
public class ListCarsForAdmin extends HttpServlet {
    private final CarService carService = CarService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cars", carService.findAllCar());
        req.getRequestDispatcher(JspHelper.getPath("listCarsForAdmin"))
                .forward(req, resp);
    }
}
