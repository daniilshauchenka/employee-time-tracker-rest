package by.andersen.tracker.dao.impl;

import by.andersen.tracker.dao.ITimeDao;
import by.andersen.tracker.dao.database.HibernateConfig;
import by.andersen.tracker.dao.exception.DaoException;
import by.andersen.tracker.model.Time;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
            Query<Time> query = session.createQuery("UPDATE  Time t set t.isDeleted = true where  t.id = :id", Time.class);
            query.setParameter("id", id);
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
            Time time = session.get(Time.class, id);
            transaction.commit();
            return time;
        } catch (HibernateException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Time> getList(int limit, int offset) throws DaoException {
        System.out.println("get time list");
        List<Time> list;
        try (Session session = HibernateConfig.getSession()) {
            Query<Time> query = session.createQuery("from Time t where t.isDeleted=false ORDER BY t.timeStart DESC ", Time.class);
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            list = query.getResultList();
        } catch (HibernateException ex) {
            throw new DaoException(ex);
        }
        return list;
    }

//    public List<Time> getListWithParams(Map<String, Object> params, int limit, int offset) throws DaoException {
//        List<Time> list;
//        try (Session session = HibernateConfig.getSession()) {
//            StringBuilder queryString = new StringBuilder("from Time t where t.isDeleted=false");
//
//            if (params != null && !params.isEmpty()) {
//                for (Map.Entry<String, Object> entry : params.entrySet()) {
//
//                    if (entry.getKey().equals("timeStart")) {
//                        queryString.append(" and t.timeStart >= :timeStart");
//                    } else if (entry.getKey().equals("timeEnd")) {
//                        queryString.append(" and t.timeEnd <= :timeEnd");
//                    } else {
//                        queryString.append(" and t.").append(entry.getKey()).append(" = :").append(entry.getKey());
//                    }
//                }
//            }
//
//            queryString.append(" ORDER BY t.timeStart DESC");
//
//            Query<Time> query = session.createQuery(queryString.toString(), Time.class);
//
//            if (params != null && !params.isEmpty()) {
//                for (Map.Entry<String, Object> entry : params.entrySet()) {
//                    query.setParameter(entry.getKey(), entry.getValue());
//                }
//            }
//
//            query.setFirstResult(offset);
//            query.setMaxResults(limit);
//            list = query.getResultList();
//        } catch (HibernateException ex) {
//            throw new DaoException(ex);
//        }
//        return list;
//    }


    public List<Time> getListWithParams(Map<String, Object> params, int limit, int offset) throws DaoException {
        List<Time> list;
        try (Session session = HibernateConfig.getSession()) {
            StringBuilder queryString = new StringBuilder("from Time t where t.isDeleted = false");

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

            // Дополнительные условия для других параметров, если есть
            // ...

            queryString.append(" ORDER BY t.timeStart DESC");

            Query<Time> query = session.createQuery(queryString.toString(), Time.class);

            // Установка параметров запроса Hibernate для времени начала, окончания и employeeId
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
//    public List<Time> getListWithParams(Map<String, Object> params, int limit, int offset) throws DaoException {
//        List<Time> list;
//        try (Session session = HibernateConfig.getSession()) {
//            StringBuilder queryString = new StringBuilder("from Time t where t.isDeleted = false");
//
//            if (params != null && !params.isEmpty()) {
//                for (Map.Entry<String, Object> entry : params.entrySet()) {
//                    queryString.append(" and ");
//
//                    switch (entry.getKey()) {
//                        case "timeStart" -> queryString.append("t.timeStart >= :timeStart");
//                        case "timeEnd" -> queryString.append("t.timeEnd <= :timeEnd");
//                        case "employeeId" -> queryString.append("t.employee.id = :employeeId");
//                        default -> queryString.append("t.").append(entry.getKey()).append(" = :").append(entry.getKey());
//                    }
//                }
//            }
//
//            queryString.append(" ORDER BY t.timeStart DESC");
//
//            Query<Time> query = session.createQuery(queryString.toString(), Time.class);
////todo wtf
//            if (params != null && !params.isEmpty()) {
//                for (Map.Entry<String, Object> entry : params.entrySet()) {
//                    if (entry.getKey().equals("employeeId") && entry.getValue() instanceof String) {
//                        query.setParameter(entry.getKey(), Integer.parseInt(entry.getValue().toString()));
//                    } else {
//                        query.setParameter(entry.getKey(), entry.getValue());
//                    }
//                }
//            }
//
//            query.setFirstResult(offset);
//            query.setMaxResults(limit);
//            list = query.getResultList();
//        } catch (HibernateException ex) {
//            throw new DaoException(ex);
//        }
//        return list;
//    }

}
