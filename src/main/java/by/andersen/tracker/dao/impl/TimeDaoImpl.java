package by.andersen.tracker.dao.impl;

import by.andersen.tracker.dao.ITimeDao;
import by.andersen.tracker.dao.database.HibernateConfig;
import by.andersen.tracker.dao.exception.DaoException;
import by.andersen.tracker.model.Time;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

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
            //Query query = session.createQuery("DELETE from Time t where  t.id = :id"  );
            query.setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            throw new DaoException(ex);
        }
    }



    public List<Time> getListWithParams(Map<String, Object> params, int limit, int offset) throws DaoException {
        List<Time> list;
        try (Session session = HibernateConfig.getSession()) {
            StringBuilder queryString = new StringBuilder("from Time t LEFT JOIN FETCH t.employee LEFT JOIN FETCH t.task where t.isDeleted = false");

            if (params != null && params.containsKey("timeStart")) {
                queryString.append(" and t.timeStart >= :timeStart");
                LocalDateTime startTime = LocalDateTime.parse(params.get("timeStart").toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                params.put("timeStart", startTime);
            }

            if (params != null && params.containsKey("timeEnd")) {
                queryString.append(" and t.timeEnd <= :timeEnd");
                LocalDateTime endTime = LocalDateTime.parse(params.get("timeEnd").toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                params.put("timeEnd", endTime);
            }


            if (params != null && params.containsKey("employeeId")) {
                queryString.append(" and t.employee.id = :employeeId");
                int employeeId = Integer.parseInt(params.get("employeeId").toString());
                params.put("employeeId", employeeId);
            }

            if (params != null && params.containsKey("id")) {
                queryString.append(" and t.id = :id");
                int id = Integer.parseInt(params.get("id").toString());
                params.put("id", id);
            }

            queryString.append(" ORDER BY t.timeStart DESC");

            Query<Time> query = session.createQuery(queryString.toString(), Time.class);

            for (Map.Entry<String, Object> entry : params.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }

            query.setFirstResult(offset);
            query.setMaxResults(limit);
            list = query.getResultList();
        } catch (HibernateException | DateTimeParseException | NumberFormatException ex) {
            throw new DaoException(ex);
        }
        return list;
    }
}
