package dao;

import model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class UserDaoHibernate implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoHibernate.class);

    @Override
    public void add(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
        logger.debug("User added!");
    }

    @Override
    public void delete(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(getUserById(id));
        tx1.commit();
        session.close();
        logger.debug("User deleted!");
    }

    @Override
    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
        logger.debug("User updated!");
    }

    @Override
    public User getUserByLogin(String login) {
        User returnUser = new User();
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                returnUser = user;
            }
        }
        return returnUser;
    }

    @Override
    public User getUserById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }

    @Override
    public boolean isUserExists(User user) {
        User newUser = getUserByLogin(user.getLogin());
        return newUser.getLogin() != null;
    }

    @Override
    public String getUserRole(User user) {
        return getUserByLogin(user.getLogin()).getRole().toString();
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User").list();
    }
}
