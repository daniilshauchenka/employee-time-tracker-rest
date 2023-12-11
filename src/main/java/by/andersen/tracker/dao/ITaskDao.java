package by.andersen.tracker.dao;

import by.andersen.tracker.model.Task;
import by.andersen.tracker.dao.exception.DaoException;
import by.andersen.tracker.model.Time;

import java.util.List;
import java.util.Map;

public interface ITaskDao {
    void add(Task task) throws DaoException;

    void edit(Task task) throws DaoException;

    void delete(int id) throws DaoException;

    List<Task> getByParams(Map<String, Object> params, int limit, int offset) throws DaoException;

 }
