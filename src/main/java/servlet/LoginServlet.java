package servlet;

import dao.UserDao;
import dao.impl.UserDaoHibernate;
import model.Role;
import model.User;
import org.apache.log4j.Logger;
import utils.HashUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    private static final UserDao userDao = new UserDaoHibernate();
    private static final Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = userDao.getUserByLogin(login);
        if (user.getId() != 0) {
            String hashedPassword = HashUtil.getSHA512SecurePassword(password, user.getSalt());
            if (user.getPassword().equals(hashedPassword)) {
                login(request, response, user);
            } else {
                request.setAttribute("wrongPassword", true);
                logger.debug("Wrong password");
                request.getRequestDispatcher("/login").forward(request, response);
            }
        } else {
            request.setAttribute("userDoesntExist", true);
            logger.debug("User does not exist: " + user.toString());
            request.getRequestDispatcher("/login").forward(request, response);
        }
    }

    private final void login(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        request.getSession().setAttribute("user", user);
        if (user.getRole().equals(Role.ADMIN)) {
            response.sendRedirect("/admin/marketplace");
        } else if (user.getRole().equals(Role.USER)) {
            response.sendRedirect("/marketplace");
        } else {
            logger.debug("Undefined role");
        }
        logger.debug("Logged in! User: " + user.toString());
    }
}
