package by.andersen.tracker.dao.impl;

import by.andersen.tracker.dao.IEmployeeDao;
import by.andersen.tracker.dao.database.HibernateConfig;
import by.andersen.tracker.dao.exception.DaoException;
import by.andersen.tracker.model.Employee;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

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
                //////////////////
                session.update(existingEmployee);
            }

            session.getTransaction().commit();
        } catch (HibernateException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        System.err.println("\n\n\nDELETING====\n\n\n");
        try (Session session = HibernateConfig.getSession()) {
            session.beginTransaction();
            Query query = session.createQuery("UPDATE Employee e SET e.isDeleted = true WHERE e.id = :deletingId");
            query.setParameter("deletingId", id);
            int rowCount = query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public Employee getById(int id) throws DaoException {
        Employee employee = null;
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSession()) {
            transaction = session.beginTransaction();
            employee = session.get(Employee.class, id);
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(ex);
        }
        return employee;
    }

    @Override
    public List<Employee> getList(int limit, int offset) throws DaoException {
        List<Employee> list = new ArrayList<Employee>();
        try (Session session = HibernateConfig.getSession()) {

            Query<Employee> query = session.createQuery("from Employee e where e.isDeleted=false ORDER BY e.id ASC ", Employee.class);
            query.setFirstResult(offset);
            query.setMaxResults(limit);

            list = query.getResultList();
        } catch (HibernateException ex) {
            throw new DaoException(ex);
        }
        return list;
    }
}
