package dao.impl;

import dao.GenericDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    @Override
    public void add(T object) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(object);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(T object) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(object);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(T object) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(object);
        tx1.commit();
        session.close();
    }

    @Override
    public T getItemById(Class<T> clazz, long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(clazz, id);
    }

    @Override
    public List<T> getAllItems(Class<T> clazz) {
        return (List<T>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From " + clazz.getName()).list();
    }
}
