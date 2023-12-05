package by.andersen.tracker.service.impl;

import by.andersen.tracker.dao.DaoProvider;
import by.andersen.tracker.dao.ITaskDao;
import by.andersen.tracker.dao.exception.DaoException;
import by.andersen.tracker.model.Task;
import by.andersen.tracker.service.ITaskService;
import by.andersen.tracker.service.exception.ServiceException;

public class TaskServiceImpl implements ITaskService {
    ITaskDao taskDao = DaoProvider.getInstance().getTaskDao();

    @Override
    public void addTask(Task task) throws ServiceException {
        try {
            taskDao.addTask(task);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void editTask(Task task) throws ServiceException {
        try {
            taskDao.editTask(task);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void deleteTask(int id) throws ServiceException {
        try {
            taskDao.deleteTask(id);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Task getById(int id) throws ServiceException {
        return null;
    }
}
