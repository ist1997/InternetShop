package servlet;

import dao.OrderDao;
import dao.UserDao;
import dao.impl.CodeDao;
import dao.impl.OrderDaoHibernate;
import dao.impl.UserDaoHibernate;
import model.Code;
import model.Order;
import model.User;
import service.MailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BuyGoodServlet", value = "/buy")
public class BuyGoodServlet extends HttpServlet {

    private static final MailService mailService = new MailService();
    private static final CodeDao codeDao = new CodeDao();
    private static final UserDao userDao = new UserDaoHibernate();
    private static final OrderDao orderDao = new OrderDaoHibernate();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long orderId = Long.valueOf(req.getParameter("order_id"));
        String codeValue = req.getParameter("code");
        User user = (User) req.getSession().getAttribute("user");
        Code code = new Code(codeValue, user.getId(), orderId);
        if (codeDao.checkCode(code)) {
            User userFromDb = userDao.getItemById(User.class, user.getId());
            Order orderToDelete = userFromDb.getOrder();
            orderDao.delete(orderToDelete);
            resp.sendRedirect("/success");
        } else {
            resp.getWriter().println("Failed");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User userFromSession = (User) req.getSession().getAttribute("user");
        User userFromDb = userDao.getItemById(User.class, userFromSession.getId());
        String codeValue = mailService.sendMail(userFromDb.getEmail());
        long orderId = userFromDb.getOrder().getId();
        Code code = new Code(codeValue, userFromDb.getId(), orderId);
        codeDao.addCode(code);
        req.setAttribute("order_id", orderId);
        resp.sendRedirect("/enterCode");
    }
}
