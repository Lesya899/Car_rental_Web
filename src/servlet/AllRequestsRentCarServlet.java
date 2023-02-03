package servlet;




import dto.RentDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.RentService;
import util.JspHelper;

import java.io.IOException;
import java.util.List;

import static util.UrlPath.ALL_REQUESTS_RENT_CAR;

@WebServlet(ALL_REQUESTS_RENT_CAR)
public class AllRequestsRentCarServlet extends HttpServlet {

    private static final RentService rentService = RentService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RentDto> rentlDto = rentService.findAllRequests();
        req.setAttribute("requestCarRent", rentlDto);
        req.getRequestDispatcher(JspHelper.getPath("allRequestsRentCar"))
                .forward(req, resp);
    }
}

