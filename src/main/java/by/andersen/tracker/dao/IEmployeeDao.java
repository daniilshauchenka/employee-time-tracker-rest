package by.andersen.tracker.dao;

import by.andersen.tracker.dao.exception.DaoException;
import by.andersen.tracker.model.Employee;

import java.util.List;

public interface IEmployeeDao {
    void addEmployee(Employee employee) throws DaoException;

    void editEmployee(Employee employee) throws DaoException;

    void deleteEmployee(int id) throws DaoException;

    Employee getById(int id) throws DaoException;

    List<Employee> getList(int limit, int offset) throws DaoException;
}
