
  package filter;

  import dto.UserDto;
  import entity.Role;
  import jakarta.servlet.*;
  import jakarta.servlet.annotation.WebFilter;
  import jakarta.servlet.http.HttpServletRequest;
  import jakarta.servlet.http.HttpServletResponse;
  import java.io.IOException;
  import java.util.Set;
  import static util.UrlPath.*;

  @WebFilter("/*")
  public class AuthorizationFilter implements Filter {

      private static final Set<String> USER_PATH = Set.of(LOGIN,REGISTRATION, CARS,CAR_DESCRIPTION, PAGE_USER, MAKE_REQUEST_TO_RENT_CAR,CLIENT_REQUESTS,DESCRIPTION_REQUESTS, IMAGES,LOGOUT,LOCALE);
      private static final Set<String> ADMIN_PATHS = Set.of(LOGIN,LOGOUT, LIST_CARS_FOR_ADMIN, PROCESS_REQUEST, ALL_REQUESTS_RENT_CAR, ADMIN, LOCALE, CHANGE_STATUS, IMAGES);

      @Override
     public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
          var uri = ((HttpServletRequest) servletRequest).getRequestURI();
          if ((isAdminPath(uri) && isAdminLogged(servletRequest))) {
              filterChain.doFilter(servletRequest, servletResponse);
          } else if (isPublicPath(uri) || isUserLoggedIn(servletRequest)) {
              filterChain.doFilter(servletRequest, servletResponse);
          } else {var prevPage = ((HttpServletRequest) servletRequest).getHeader("referer");
              ((HttpServletResponse) servletResponse).sendRedirect(prevPage != null ? prevPage : LOGIN);
          }
      }


      private boolean isUserLoggedIn(ServletRequest servletRequest) {
          var user = (UserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
          return  user!=null && user.getRole()==Role.USER;
      }


      private boolean isAdminLogged(ServletRequest servletRequest) {
          var user = (UserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
          return user!=null && user.getRole() == Role.ADMIN;
      }

      private boolean isPublicPath(String uri) {
          return USER_PATH.stream().anyMatch(uri::startsWith);
     }

      private boolean isAdminPath(String uri) {
          return ADMIN_PATHS.stream().anyMatch(uri::startsWith);
     }

  }


