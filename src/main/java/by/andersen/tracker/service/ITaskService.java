package by.andersen.tracker.service;

import by.andersen.tracker.model.Task;
import by.andersen.tracker.service.exception.ServiceException;

public interface ITaskService {
    void addTask(Task task) throws ServiceException;

    void editTask(Task task) throws ServiceException;

    void deleteTask(int id) throws ServiceException;

    Task getById(int id) throws ServiceException;
}
