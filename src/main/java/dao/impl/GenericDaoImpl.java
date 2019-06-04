package dao.impl;

import dao.GenericDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    @Override
    public void add(T object) {
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
        }
    }

    @Override
    public void delete(T object) {
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
        }
    }

    @Override
    public void update(T object) {
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(object);
            transaction.commit();
        }
    }

    public T getItemById(Class<T> clazz, long id) {
        T object;
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            object = session.get(clazz, id);
        }
        return object;
    }

    @Override
    public List<T> getAllItems(Class<T> clazz) {
        List<T> objects;
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            objects = (List<T>) session
                    .createQuery("From " + clazz.getName())
                    .list();
        }
        return objects;
    }
}
