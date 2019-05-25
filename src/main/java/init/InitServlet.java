package init;

import dao.GoodDao;
import dao.UserDao;
import dao.impl.GoodDaoHibernate;
import dao.impl.UserDaoHibernate;
import model.Good;
import model.Role;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "InitServlet", value = "/init", loadOnStartup = 1)
public class InitServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        UserDao userDao = new UserDaoHibernate();

        User ist = new User("ist1997", "0000", "ilkivsviatoslav@gmail.com", Role.USER);
        User admin = new User("admin", "admin", "admin@gmail.com", Role.ADMIN);

        userDao.add(ist);
        userDao.add(admin);

        Good bmw = new Good("bmw", "x6", 40000);
        GoodDao goodDao = new GoodDaoHibernate();
        goodDao.add(bmw);


    }
}
