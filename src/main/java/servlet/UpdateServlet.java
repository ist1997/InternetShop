package servlet;

import dao.GoodDao;
import dao.GoodDaoHibernate;
import dao.UserDao;
import dao.UserDaoHibernate;
import model.Good;
import model.Role;
import model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateServlet", value = "/update")
public class UpdateServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(UpdateServlet.class);
    private static final UserDao USER_DAO = new UserDaoHibernate();
    private static final GoodDao GOOD_DAO = new GoodDaoHibernate();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        switch (type) {
            case "user":
                updateUser(req, resp);
                break;
            case "good":
                updateGood(req, resp);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        String type = req.getParameter("type");
        switch (type) {
            case "user":
                User user = USER_DAO.getUserById(id);
                req.setAttribute("user", user);
                req.getRequestDispatcher("update_user.jsp").forward(req, resp);
                break;
            case "good":
                Good good = GOOD_DAO.getGoodById(id);
                req.setAttribute("good", good);
                req.getRequestDispatcher("update_good.jsp").forward(req, resp);
                break;
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        long roleId = Long.parseLong(request.getParameter("roleId"));
        String salt = request.getParameter("salt");

        User user = new User(id, login, password, email, Role.values()[(int) roleId], salt);
        USER_DAO.update(user);

        LOGGER.info("Added user: " + user.toString());
        response.sendRedirect("userlist.jsp");
    }

    private void updateGood(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price").split(" ")[0]);

        Good good = new Good(id, name, description, price);
        GOOD_DAO.update(good);

        LOGGER.info("Added good: " + good.toString());
        response.sendRedirect("marketplace.jsp");
    }
}
