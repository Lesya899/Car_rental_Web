package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import static util.UrlPath.LOCALE;
import static util.UrlPath.LOGIN;

@WebServlet(LOCALE)
public class LocaleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var language = req.getParameter("lang");
        req.getSession().setAttribute("lang", language);
        var prevPage = req.getHeader("referer");
        var page = prevPage != null ? prevPage : LOGIN;
        if(page.contains("?")){
            resp.sendRedirect(page + "&lang=" + language);
        }else{
            resp.sendRedirect(page + "?lang=" + language);
        }
    }
}
