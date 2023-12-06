package by.andersen.tracker.dao.impl;

import by.andersen.tracker.dao.IEmployeeDao;
import by.andersen.tracker.dao.database.HibernateConfig;
import by.andersen.tracker.dao.exception.DaoException;
import by.andersen.tracker.model.Employee;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeDaoImpl implements IEmployeeDao {

    @Override
    public void add(Employee employee) throws DaoException {

    }

    @Override
    public void edit(Employee employee) throws DaoException {

    }

    @Override
    public void delete(int id) throws DaoException {
        // NOT delete but set value deleted=true
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
        return null;
    }
}
