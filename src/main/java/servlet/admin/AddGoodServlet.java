package servlet.admin;

import dao.GoodDao;
import dao.impl.GoodDaoHibernate;
import model.Good;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminAddGoodServlet", value = "/admin/addGood")
public class AddGoodServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AddGoodServlet.class);
    private static final GoodDao goodDao = new GoodDaoHibernate();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("addGood.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));

        Good good = new Good(name, description, price);
        goodDao.add(good);

        logger.info("Added good: " + good.toString());
        req.setAttribute("goods", goodDao.getAllItems(Good.class));
        resp.sendRedirect("/admin/marketplace");
    }
}
