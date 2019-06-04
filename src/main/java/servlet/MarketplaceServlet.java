package servlet;

import dao.GoodDao;
import dao.UserDao;
import dao.impl.GoodDaoHibernate;
import dao.impl.UserDaoHibernate;
import model.Good;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserMarketplace", value = "/marketplace")
public class MarketplaceServlet extends HttpServlet {

    private static final GoodDao goodDao = new GoodDaoHibernate();
    private static final UserDao userDao = new UserDaoHibernate();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User userFromSession = (User) req.getSession().getAttribute("user");
        User userFromDb = userDao.getItemById(User.class, userFromSession.getId());
        int goodsCount = 0;
        if (userFromDb.getOrder() != null) {
            goodsCount = userFromDb.getOrder().getGoods().size();
        }
        req.setAttribute("goodsCount", goodsCount);
        req.setAttribute("goods", goodDao.getAllItems(Good.class));
        req.getRequestDispatcher("marketplace.jsp").forward(req, resp);
    }
}
