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

@WebServlet(name = "UpdateGoodServlet", value = "/admin/updateGood")
public class UpdateGoodServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UpdateGoodServlet.class);
    private static final GoodDao goodDao = new GoodDaoHibernate();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price").split(" ")[0]);

        Good good = new Good(id, name, description, price);
        goodDao.update(good);

        logger.info("Added good: " + good.toString());
        resp.sendRedirect("/admin/marketplace");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        Good good = goodDao.getItemById(Good.class, id);
        req.setAttribute("good", good);
        req.getRequestDispatcher("updateGood.jsp").forward(req, resp);
    }
}
