package by.andersen.tracker.dao.impl;

import by.andersen.tracker.dao.IEmployeeDao;
import by.andersen.tracker.dao.exception.DaoException;
import by.andersen.tracker.model.Employee;

import java.util.List;

public class EmployeeDaoImpl implements IEmployeeDao {
    @Override
    public void addEmployee(Employee employee) throws DaoException {

    }

    @Override
    public void editEmployee(Employee employee) throws DaoException {

    }

    @Override
    public void deleteEmployee(int id) throws DaoException {

    }

    @Override
    public Employee getById(int id) throws DaoException {
        return null;
    }

    @Override
    public List<Employee> getList(int limit, int offset) throws DaoException {
        return null;
    }
}
