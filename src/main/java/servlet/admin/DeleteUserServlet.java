package servlet.admin;

import dao.UserDao;
import dao.impl.UserDaoHibernate;
import model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminUpdateGoodServlet", value = "/admin/deleteUser")
public class DeleteUserServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(DeleteUserServlet.class);
    private static final UserDao userDao = new UserDaoHibernate();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        userDao.delete(userDao.getItemById(User.class, id));
        logger.info("Deleted user with id = " + id);
        resp.sendRedirect("/admin/users");
    }
}
