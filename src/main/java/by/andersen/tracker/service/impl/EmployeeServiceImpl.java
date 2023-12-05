package by.andersen.tracker.service.impl;

import by.andersen.tracker.dao.DaoProvider;
import by.andersen.tracker.dao.IEmployeeDao;
import by.andersen.tracker.dao.exception.DaoException;
import by.andersen.tracker.model.Employee;
import by.andersen.tracker.service.IEmployeeService;
import by.andersen.tracker.service.exception.ServiceException;

import java.util.List;

public class EmployeeServiceImpl implements IEmployeeService {
    IEmployeeDao employeeDao = DaoProvider.getInstance().getEmployeeDao();

    @Override
    public void addEmployee(Employee employee) throws ServiceException {
        try {
            employeeDao.addEmployee(employee);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void editEmployee(Employee employee) throws ServiceException {
        try {
            employeeDao.editEmployee(employee);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void deleteEmployee(int id) throws ServiceException {
        try {
            employeeDao.deleteEmployee(id);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Employee getById(int id) throws ServiceException {
        try {
            return employeeDao.getById(id);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<Employee> getList(int limit, int offset) throws ServiceException {
        try {
            return employeeDao.getList(limit,offset);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }


}
