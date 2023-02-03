package servlet;



import dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.RentService;
import util.JspHelper;


import java.io.IOException;

import static util.UrlPath.CLIENT_REQUESTS;


@WebServlet(CLIENT_REQUESTS)
public class ClientRequestsServlet extends HttpServlet {

    private static final RentService rentService = RentService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto user =  (UserDto) req.getSession().getAttribute("user");
        var requestByUser = rentService.findRequestsByUser(user.getId());
        req.setAttribute("requestsClient", requestByUser);
        req.getRequestDispatcher(JspHelper.getPath("clientRequests"))
                .forward(req, resp);
    }

}
