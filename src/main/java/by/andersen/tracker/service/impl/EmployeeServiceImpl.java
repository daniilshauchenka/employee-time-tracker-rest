package by.andersen.tracker.service.impl;

import by.andersen.tracker.dao.DaoProvider;
import by.andersen.tracker.dao.IEmployeeDao;
import by.andersen.tracker.dao.exception.DaoException;
import by.andersen.tracker.model.Employee;
import by.andersen.tracker.service.IEmployeeService;
import by.andersen.tracker.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements IEmployeeService {
    IEmployeeDao employeeDao = DaoProvider.getInstance().getEmployeeDao();

    @Override
    public void add(Employee employee) throws ServiceException {
        try {
            employeeDao.add(employee);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void edit(Employee employee) throws ServiceException {
        try {
            employeeDao.edit(employee);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            employeeDao.delete(id);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<Employee> getByParams(Map<String, Object> params, int limit, int offset) throws ServiceException {
        try {
            return employeeDao.getByParams(params,limit, offset);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }


}
