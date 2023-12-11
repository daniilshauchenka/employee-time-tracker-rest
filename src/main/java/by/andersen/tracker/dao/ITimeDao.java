package by.andersen.tracker.dao;

import by.andersen.tracker.model.Employee;
import by.andersen.tracker.model.Time;
import by.andersen.tracker.dao.exception.DaoException;

import java.util.List;
import java.util.Map;

public interface ITimeDao {
    void add(Time time) throws DaoException;

    void edit(Time time) throws DaoException;

    void delete(int id) throws DaoException;

    List<Time> getByParams(Map<String, Object> params, int limit, int offset) throws DaoException;
}
