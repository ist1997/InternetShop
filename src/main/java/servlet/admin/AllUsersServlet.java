package servlet.admin;

import dao.UserDao;
import dao.impl.UserDaoHibernate;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminAllUsersServlet", value = "/admin/users")
public class AllUsersServlet extends HttpServlet {

    private static final UserDao userDao = new UserDaoHibernate();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", userDao.getAllItems(User.class));
        req.getRequestDispatcher("userList.jsp").forward(req, resp);
    }
}
