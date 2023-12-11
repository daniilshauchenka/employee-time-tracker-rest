package by.andersen.tracker.dao.impl;

import by.andersen.tracker.dao.IEmployeeDao;
import by.andersen.tracker.dao.database.HibernateConfig;
import by.andersen.tracker.dao.exception.DaoException;
import by.andersen.tracker.model.Employee;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;
import java.util.Map;

public class EmployeeDaoImpl implements IEmployeeDao {

    @Override
    public void add(Employee employee) throws DaoException {

        try (Session session = HibernateConfig.getSession()) {
            session.beginTransaction();

            session.save(employee);

            session.getTransaction().commit();
        } catch (HibernateException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public void edit(Employee employee) throws DaoException {
        try (Session session = HibernateConfig.getSession()) {
            session.beginTransaction();
            System.out.println("\n\n\nediting employee " + employee);
            Employee existingEmployee = session.get(Employee.class, employee.getId());
            System.out.println("\n\n\nexisting employee " + existingEmployee);
            if (existingEmployee != null) {
                existingEmployee.setName(employee.getName());
                existingEmployee.setSurname(employee.getSurname());
                existingEmployee.setLogin(employee.getLogin());
                existingEmployee.setPassword(employee.getPassword());
                session.update(existingEmployee);
            }
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        try (Session session = HibernateConfig.getSession()) {
            session.beginTransaction();
            Query query = session.createQuery("UPDATE Employee e SET e.isDeleted = true WHERE e.id = :id");
            query.setParameter("id", id);
            int rowCount = query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            throw new DaoException(ex);
        }
    }
    public List<Employee> getByParams(Map<String, Object> params, int limit, int offset) throws DaoException {
        List<Employee> list;
        try (Session session = HibernateConfig.getSession()) {
            StringBuilder queryString = new StringBuilder("from Employee e  where e.isDeleted = false");

            if (params != null && params.containsKey("id")) {
                queryString.append(" and e.id = :id");
                int id = Integer.parseInt(params.get("id").toString());
                params.put("id", id);
            }

            queryString.append(" ORDER BY e.id ASC");

            Query<Employee> query = session.createQuery(queryString.toString(), Employee.class);

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
