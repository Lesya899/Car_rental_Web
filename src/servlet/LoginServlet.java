package servlet;

//при обращении по адресу localhost:8080/login, открывается страница для ввода пользователем логина и пароля


import dto.UserDto;
import entity.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import service.UserService;
import util.JspHelper;


import java.io.IOException;

import static util.UrlPath.*;

@WebServlet(LOGIN)
public class LoginServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    /**
     *
     * RequestDispatcher получает запросы от клиента и отправляет их ресурсу (например, сервлету, файлу HTML, файлу JSP, шаблону FreeMarker или Thymeleaf) на сервере.
     * С помощью  метода getRequestDispatcher() получаем объект RequestDispatcher. Путь к ресурсу, на который надо выполнить перенаправление, передается в качестве параметра в getRequestDispatcher.
     * Затем у объекта RequestDispatcher вызывается метод forward(), в который передаются объекты HttpServletRequest и HttpServletResponse.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", Role.values());
        req.getRequestDispatcher(JspHelper.getPath("login"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.login(req.getParameter("email"), req.getParameter("password"), req.getParameter("role"))
                .ifPresentOrElse(
                        user -> onLoginSuccess(user, req, resp),
                        () -> onLoginFail(req, resp)
                );
    }

    @SneakyThrows
    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect("/login?error&email=" + req.getParameter("email"));

    }

    @SneakyThrows
    private void onLoginSuccess(UserDto user, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("user", user);
        if(user.getRole()==Role.USER) {
            resp.sendRedirect(PAGE_USER);
        }else if(user.getRole()==Role.ADMIN){
            resp.sendRedirect(ADMIN);
        }
    }
}
