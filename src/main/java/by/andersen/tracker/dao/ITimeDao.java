package by.andersen.tracker.dao;

import by.andersen.tracker.model.Employee;
import by.andersen.tracker.model.Time;
import by.andersen.tracker.dao.exception.DaoException;

import java.util.List;

public interface ITimeDao {
    void add(Time time) throws DaoException;

    void edit(Time time) throws DaoException;

    void delete(int id) throws DaoException;

    Time getById(int id) throws DaoException;

    List<Time> getList(int limit, int offset) throws DaoException;
}
