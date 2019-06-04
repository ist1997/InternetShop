package servlet;

import dao.GoodDao;
import dao.OrderDao;
import dao.UserDao;
import dao.impl.GoodDaoHibernate;
import dao.impl.OrderDaoHibernate;
import dao.impl.UserDaoHibernate;
import model.Good;
import model.Order;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "AddToOrderServlet", value = "/addToOrder")
public class AddToOrderServlet extends HttpServlet {

    private static final GoodDao goodDao = new GoodDaoHibernate();
    private static final OrderDao orderDao = new OrderDaoHibernate();
    private static final UserDao userDao = new UserDaoHibernate();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long goodId = Long.valueOf(req.getParameter("id"));
        Good good = goodDao.getItemById(Good.class, goodId);
        User userFromSession = (User) req.getSession().getAttribute("user");
        User userFromDb = userDao.getItemById(User.class, userFromSession.getId());
        Order currentOrder = userFromDb.getOrder();
        if (currentOrder != null) {
            currentOrder.getGoods().add(good);
            orderDao.update(currentOrder);
        } else {
            Order order = new Order(Arrays.asList(good), userFromDb);
            orderDao.add(order);
        }
        resp.sendRedirect("/marketplace");
    }
}
