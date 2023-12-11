package by.andersen.tracker.dao.impl;

import by.andersen.tracker.dao.ITaskDao;
import by.andersen.tracker.dao.database.HibernateConfig;
import by.andersen.tracker.dao.exception.DaoException;
import by.andersen.tracker.model.Task;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSession()) {
            transaction = session.beginTransaction();
            session.update(task);
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(ex);
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        try (Session session = HibernateConfig.getSession()) {
            session.beginTransaction();
            Query query = session.createQuery("UPDATE Task t SET t.isDeleted = true WHERE t.id = :id");
            query.setParameter("id", id);
            int rowCount = query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            throw new DaoException(ex);
        }

    }
    public List<Task> getByParams(Map<String, Object> params, int limit, int offset) throws DaoException {
        List<Task> list;
        try (Session session = HibernateConfig.getSession()) {
            StringBuilder queryString = new StringBuilder("from Task t  where t.isDeleted = false");

            if (params != null && params.containsKey("id")) {
                queryString.append(" and t.id = :id");
                int id = Integer.parseInt(params.get("id").toString());
                params.put("id", id);
            }

            queryString.append(" ORDER BY t.id ASC");

            Query<Task> query = session.createQuery(queryString.toString(), Task.class);

            for (Map.Entry<String, Object> entry : params.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }

            query.setFirstResult(offset);
            query.setMaxResults(limit);
            list = query.getResultList();
        } catch (HibernateException | NumberFormatException ex) {
            throw new DaoException(ex);
        }
        return list;
    }
}
