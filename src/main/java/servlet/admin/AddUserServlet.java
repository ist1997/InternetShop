package servlet.admin;

import dao.UserDao;
import dao.impl.UserDaoHibernate;
import model.Role;
import model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminAddUserServlet", value = "/admin/addUser")
public class AddUserServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AddUserServlet.class);
    private static final UserDao userDao = new UserDaoHibernate();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("addUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        int roleId = Integer.parseInt(req.getParameter("roleId"));

        User user = new User(login, password, email, Role.values()[roleId]);
        userDao.add(user);

        logger.info("Added user: " + user.toString());
        resp.sendRedirect("/admin/users");
    }
}
