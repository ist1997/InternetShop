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

@WebServlet(name = "DeleteGoodServlet", value = "/admin/deleteGood")
public class DeleteGoodServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(DeleteGoodServlet.class);
    private static final GoodDao goodDao = new GoodDaoHibernate();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        goodDao.delete(goodDao.getItemById(Good.class, id));
        logger.info("Deleted item with id = " + id);
        response.sendRedirect("/admin/marketplace");
    }
}
