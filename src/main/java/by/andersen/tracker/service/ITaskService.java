package by.andersen.tracker.service;

import by.andersen.tracker.model.Task;
import by.andersen.tracker.service.exception.ServiceException;

import java.util.List;

public interface ITaskService {
    void add(Task task) throws ServiceException;

    void edit(Task task) throws ServiceException;

    void delete(int id) throws ServiceException;

    Task getById(int id) throws ServiceException;

    List<Task> getList(int limit, int offset) throws ServiceException;
}
