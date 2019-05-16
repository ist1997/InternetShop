package dao;

import model.Good;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class GoodDaoHibernate implements GoodDao {

    private static final Logger LOGGER = Logger.getLogger(GoodDaoHibernate.class);

    @Override
    public void add(Good good) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(good);
        tx1.commit();
        session.close();
        LOGGER.debug("Good added!");
    }

    @Override
    public void delete(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(getGoodById(id));
        tx1.commit();
        session.close();
        LOGGER.debug("Good deleted!");
    }

    @Override
    public void update(Good good) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(good);
        tx1.commit();
        session.close();
        LOGGER.debug("Good updated!");

    }

    @Override
    public Good getGoodById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Good.class, id);
    }

    @Override
    public List<Good> getAllGoods() {
        return (List<Good>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Good").list();
    }
}
