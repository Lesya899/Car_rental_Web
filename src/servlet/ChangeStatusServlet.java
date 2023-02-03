package servlet;


import dto.RentDto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import service.DescriptionRequestService;
import service.RentService;
import util.JspHelper;
import static util.UrlPath.CHANGE_STATUS;


@WebServlet(CHANGE_STATUS)
public class ChangeStatusServlet extends HttpServlet {

    private static final DescriptionRequestService descriptionRequestService = DescriptionRequestService.getInstance();
    private static final RentService rentService = RentService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Integer id = Integer.valueOf(req.getParameter("requestId"));
        descriptionRequestService.findRequestById(id).ifPresentOrElse(rentDto -> {
            forwardRentDto(req, resp, rentDto);
        }, () -> {
            send(resp);
        });

    }
    @SneakyThrows
    private void forwardRentDto(HttpServletRequest req, HttpServletResponse resp, RentDto rentDto) {
        rentService.processTheRequest(rentDto);
        req.setAttribute("requestCarRent", rentService.findAllRequests());
        req.getRequestDispatcher(JspHelper.getPath("allRequestsRentCar"))
                .forward(req, resp);
    }

    @SneakyThrows
    private void send(HttpServletResponse resp) {
        resp.setStatus(400);
        resp.sendError(400, "NO REQUEST FOUND");
    }

}

