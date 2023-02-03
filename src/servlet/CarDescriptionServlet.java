package servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CarService;
import util.JspHelper;
import java.io.IOException;
import static util.UrlPath.*;

@WebServlet(CAR_DESCRIPTION)
public class CarDescriptionServlet extends HttpServlet {


    private final CarService carService = CarService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("carId"));
        req.setAttribute("car", carService.findById(id));
        req.getRequestDispatcher(JspHelper.getPath("carDescription"))
                .forward(req, resp);
    }
}




