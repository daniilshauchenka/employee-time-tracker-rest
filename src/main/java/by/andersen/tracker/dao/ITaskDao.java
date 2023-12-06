package by.andersen.tracker.dao;

import by.andersen.tracker.model.Task;
import by.andersen.tracker.dao.exception.DaoException;

import java.util.List;

public interface ITaskDao {
    void add(Task task) throws DaoException;

    void edit(Task task) throws DaoException;

    void delete(int id) throws DaoException;

    Task getById(int id) throws DaoException;

    List<Task> getList(int limit, int offset) throws DaoException;
}
