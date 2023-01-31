package servlet;


import dto.RentDto;
import entity.RequestStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.RentService;
import util.JspHelper;

import java.io.IOException;
import java.util.List;

import static util.UrlPath.ALL_REQUESTS_RENT_CAR;

@WebServlet(ALL_REQUESTS_RENT_CAR)
public class AllRequestsRentCarServlet extends HttpServlet {

    private static final RentService rentService = RentService.getInstance();
    //private static final AddRequestStatusService addRequestStatusService = AddRequestStatusService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RentDto> rentlDto = rentService.findAllRequests();
        req.setAttribute("requestCarRent", rentlDto);
        req.setAttribute("status", RequestStatus.values());
        req.getRequestDispatcher(JspHelper.getPath("allRequestsRentCar"))
                .forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String rentId = String.valueOf(session.getAttribute("carRental"));
       //var requestRentDto = RequestRentDto.builder()
                //.id(Integer.valueOf(rentId))
                //.statusRequests(req.getParameter("status"))
                //.build();

        //addRequestStatusService.addStatusRequest(requestRentDto);
        //resp.sendRedirect("allRequestsRentCar");


    }
}
