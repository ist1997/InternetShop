package servlet.admin;

import dao.GoodDao;
import dao.impl.GoodDaoHibernate;
import model.Good;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminMarketplace", value = "/admin/marketplace")
public class AdminMarketplaceServlet extends HttpServlet {

    private static final GoodDao goodDao = new GoodDaoHibernate();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("goods", goodDao.getAllItems(Good.class));
        req.getRequestDispatcher("adminMarketplace.jsp").forward(req, resp);
    }
}
