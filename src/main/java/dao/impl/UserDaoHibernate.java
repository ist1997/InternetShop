package dao.impl;

import dao.UserDao;
import model.User;
import org.hibernate.Session;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class UserDaoHibernate extends GenericDaoImpl<User> implements UserDao {

    @Override
    public User getUserByLogin(String login) {
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            List<User> users = ((List<User>) session
                    .createQuery("From User WHERE login = '" + login + "'")
                    .list());
            if (users.size() != 0) {
                return users.get(0);
            } else {
                return new User();
            }
        }
    }

    @Override
    public boolean isUserExists(User user) {
        User newUser = getUserByLogin(user.getLogin());
        return newUser.getLogin() != null;
    }

    @Override
    public String getUserRole(User user) {
        return getUserByLogin(user
                .getLogin())
                .getRole()
                .toString();
    }

}
