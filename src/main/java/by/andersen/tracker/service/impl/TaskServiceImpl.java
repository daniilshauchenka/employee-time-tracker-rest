package by.andersen.tracker.service.impl;

import by.andersen.tracker.dao.DaoProvider;
import by.andersen.tracker.dao.ITaskDao;
import by.andersen.tracker.dao.exception.DaoException;
import by.andersen.tracker.model.Task;
import by.andersen.tracker.service.ITaskService;
import by.andersen.tracker.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public class TaskServiceImpl implements ITaskService {
    ITaskDao taskDao = DaoProvider.getInstance().getTaskDao();

    @Override
    public void add(Task task) throws ServiceException {
        try {
            taskDao.add(task);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void edit(Task task) throws ServiceException {
        try {
            taskDao.edit(task);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            taskDao.delete(id);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<Task> getByParams(Map<String, Object> params, int limit, int offset) throws ServiceException {
        try {
            return taskDao.getByParams(params,limit, offset);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }
}
