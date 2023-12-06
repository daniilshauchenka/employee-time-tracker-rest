package by.andersen.tracker.dao.impl;

import by.andersen.tracker.dao.ITaskDao;
import by.andersen.tracker.dao.exception.DaoException;
import by.andersen.tracker.model.Task;

import java.util.List;

public class TaskDaoImpl implements ITaskDao {

    @Override
    public void add(Task task) throws DaoException {

    }

    @Override
    public void edit(Task task) throws DaoException {

    }

    @Override
    public void delete(int id) throws DaoException {

    }

    @Override
    public Task getById(int id) throws DaoException {
        return null;
    }

    @Override
    public List<Task> getList(int limit, int offset) throws DaoException {
        return null;
    }
}
