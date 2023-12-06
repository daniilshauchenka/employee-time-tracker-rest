package by.andersen.tracker.dao.impl;

import by.andersen.tracker.dao.ITimeDao;
import by.andersen.tracker.dao.database.HibernateConfig;
import by.andersen.tracker.dao.exception.DaoException;
import by.andersen.tracker.model.Time;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TimeDaoImpl implements ITimeDao {
    @Override
    public void add(Time time) throws DaoException {
        try (Session session = HibernateConfig.getSession()) {
            session.beginTransaction();
            session.save(time);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public void edit(Time time) throws DaoException {
        try (Session session = HibernateConfig.getSession()) {
                session.beginTransaction();
                session.saveOrUpdate(time);
                session.getTransaction().commit();
        } catch (HibernateException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        try (Session session = HibernateConfig.getSession()) {
            session.beginTransaction();
            Query query = session.createQuery("UPDATE  Time t set t.isDeleted = true where  t.id = :id");
            query.setParameter("id",id);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public Time getById(int id) throws DaoException {
        try (Session session = HibernateConfig.getSession()) {
            session.beginTransaction();
            Transaction transaction = session.beginTransaction();
            Time time = session.get(Time.class,id);
            transaction.commit();
            return time;
        } catch (HibernateException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Time> getList(int limit, int offset) throws DaoException {
        try (Session session = HibernateConfig.getSession()) {
            Query query = session.createQuery("from Time t where t.isDeleted=false");
            return (List<Time>) query.getResultList();
        } catch (HibernateException ex) {
            throw new DaoException(ex);
        }
    }
}
