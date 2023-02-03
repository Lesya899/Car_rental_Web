package servlet;


import dto.RentDto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import service.DescriptionRequestService;
import util.JspHelper;
import static util.UrlPath.PROCESS_REQUEST;


@WebServlet(PROCESS_REQUEST)
public class ProcessRequestServlet extends HttpServlet {

    private static final DescriptionRequestService descriptionRequestService = DescriptionRequestService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Integer id = Integer.valueOf(req.getParameter("requestId"));
        descriptionRequestService.findRequestById(id).ifPresentOrElse(rentDto -> {
            forwardRequest(req, resp, rentDto);
        }, () -> {
            send(resp);
        });
    }
    @SneakyThrows
    private void forwardRequest(HttpServletRequest req, HttpServletResponse resp, RentDto rentDto) {
        req.setAttribute("rental", rentDto);
        req.getRequestDispatcher(JspHelper.getPath("processRequest"))
                .forward(req, resp);
    }

    @SneakyThrows
    private void send(HttpServletResponse resp) {
        resp.setStatus(400);
        resp.sendError(400, "NO REQUEST FOUND");
    }
}

