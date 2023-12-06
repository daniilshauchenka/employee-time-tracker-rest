package by.andersen.tracker.dao;

import by.andersen.tracker.dao.exception.DaoException;
import by.andersen.tracker.model.Employee;

import java.util.List;

public interface IEmployeeDao {
    void add(Employee employee) throws DaoException;

    void edit(Employee employee) throws DaoException;

    void delete(int id) throws DaoException;

    Employee getById(int id) throws DaoException;

    List<Employee> getList(int limit, int offset) throws DaoException;
}
