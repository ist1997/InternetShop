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

@WebServlet(name = "AdminUpdateUserServlet", value = "/admin/updateUser")
public class UpdateUserServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UpdateGoodServlet.class);
    private static final UserDao userDao = new UserDaoHibernate();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        User user = userDao.getItemById(User.class, id);
        req.setAttribute("user", user);
        req.getRequestDispatcher("updateUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        long roleId = Long.parseLong(req.getParameter("roleId"));
        String salt = req.getParameter("salt");

        User user = new User(id, login, password, email, Role.values()[(int) roleId], salt);
        userDao.update(user);

        logger.info("Added user: " + user.toString());
        resp.sendRedirect("/admin/users");
    }
}
