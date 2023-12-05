package by.andersen.tracker.dao;

import by.andersen.tracker.model.Task;
import by.andersen.tracker.dao.exception.DaoException;

public interface ITaskDao {
    void addTask(Task task) throws DaoException;

    void editTask(Task task) throws DaoException;

    void deleteTask(int id) throws DaoException;

    Task getById(int id) throws DaoException;
}
