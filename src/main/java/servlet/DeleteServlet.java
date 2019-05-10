package servlet;

import dao.GoodDao;
import dao.UserDao;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteServlet", value = "/delete")
public class DeleteServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(DeleteServlet.class);

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
        UserDao.delete(id);
        logger.info("Deleted user with id = " + id);
        response.sendRedirect("userlist.jsp");
    }

    private void deleteGood(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        GoodDao.delete(id);
        logger.info("Deleted item with id = " + id);
        response.sendRedirect("marketplace.jsp");
    }
}
