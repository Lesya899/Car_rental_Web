package servlet;

import dto.RentDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import service.DescriptionRequestService;
import util.JspHelper;
import java.io.IOException;
import static util.UrlPath.DESCRIPTION_REQUESTS;


@WebServlet(DESCRIPTION_REQUESTS)
public class DescriptionRequestServlet extends HttpServlet {

    private static final DescriptionRequestService descriptionRequestService = DescriptionRequestService.getInstance();


    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("rentId"));
        descriptionRequestService.findRequestById(id).ifPresentOrElse(rentDto -> {
            forwardRequest(req, resp, rentDto);
        }, () -> {
            send(resp);
    });
}
    @SneakyThrows
    private void forwardRequest(HttpServletRequest req, HttpServletResponse resp, RentDto rentDto) {
        req.setAttribute("information", rentDto);
        req.getRequestDispatcher(JspHelper.getPath("descriptionRequest"))
                .forward(req, resp);
    }

    @SneakyThrows
    private void send(HttpServletResponse resp) {
        resp.setStatus(400);
        resp.sendError(400, "NO REQUEST FOUND");
    }
 }

