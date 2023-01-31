package servlet;

//при нажатии на ссылку с названием автомобиля сервер перенаправляет клиента на страницу с описанием автомобиля

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CarDescriptionService;
import util.JspHelper;


import java.io.IOException;

import static util.UrlPath.*;

@WebServlet(CAR_DESCRIPTION)
public class CarDescriptionServlet extends HttpServlet {


    private final CarDescriptionService carDescriptionService = CarDescriptionService.getInstance();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("carId"));//извлекаем из запроса параметр carId
        req.setAttribute("car", carDescriptionService.findById(id)); //находим в БД по данному id автомобиль и устанавливаем
        req.getRequestDispatcher(JspHelper.getPath("carDescription"))
                .forward(req, resp);
    }
}




