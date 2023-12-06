package by.andersen.tracker.dao.impl;

import by.andersen.tracker.dao.ITaskDao;
import by.andersen.tracker.dao.database.HibernateConfig;
import by.andersen.tracker.dao.exception.DaoException;
import by.andersen.tracker.model.Employee;
import by.andersen.tracker.model.Task;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TaskDaoImpl implements ITaskDao {

    @Override
    public void add(Task task) throws DaoException {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSession()) {
            transaction = session.beginTransaction();
            session.save(task);
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(ex);
        }

    }

    @Override
    public void edit(Task task) throws DaoException {

    }

    @Override
    public void delete(int id) throws DaoException {

    }

    @Override
    public Task getById(int id) throws DaoException {
        Task task = null;
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSession()) {
            transaction = session.beginTransaction();
            task = session.get(Task.class, id);
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(ex);
        }
        return task;
    }

    @Override
    public List<Task> getList(int limit, int offset) throws DaoException {
        return null;
    }
}
