package by.andersen.tracker.dao;

import by.andersen.tracker.dao.exception.DaoException;
import by.andersen.tracker.model.Employee;
import by.andersen.tracker.model.Time;

import java.util.List;
import java.util.Map;

public interface IEmployeeDao {
    void add(Employee employee) throws DaoException;

    void edit(Employee employee) throws DaoException;

    void delete(int id) throws DaoException;

    List<Employee> getByParams(Map<String, Object> params, int limit, int offset) throws DaoException;
}
