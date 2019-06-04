package servlet;

import dao.UserDao;
import dao.impl.UserDaoHibernate;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EnterCodeServlet", value = "/enterCode")
public class EnterCodeServlet extends HttpServlet {

    private static final UserDao userDao = new UserDaoHibernate();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User userFromSession = (User) req.getSession().getAttribute("user");
        User userFromDb = userDao.getItemById(User.class, userFromSession.getId());
        long orderId = userFromDb.getOrder().getId();
        req.setAttribute("order_id", orderId);
        req.getRequestDispatcher("entercode.jsp").forward(req, resp);
    }
}
