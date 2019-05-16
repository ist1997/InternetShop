package servlet;

import dao.GoodDao;
import dao.GoodDaoHibernate;
import dao.UserDao;
import dao.UserDaoHibernate;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteServlet", value = "/delete")
public class DeleteServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(DeleteServlet.class);
    private static final UserDao USER_DAO = new UserDaoHibernate();
    private static final GoodDao GOOD_DAO = new GoodDaoHibernate();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        switch (type) {
            case "user":
                deleteUser(request, response);
                break;
            case "good":
                deleteGood(request, response);
                break;
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        USER_DAO.delete(id);
        LOGGER.info("Deleted user with id = " + id);
        response.sendRedirect("userlist.jsp");
    }

    private void deleteGood(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        GOOD_DAO.delete(id);
        LOGGER.info("Deleted item with id = " + id);
        response.sendRedirect("marketplace.jsp");
    }
}
