package by.andersen.tracker.service;

import by.andersen.tracker.model.Task;
import by.andersen.tracker.model.Time;
import by.andersen.tracker.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface ITaskService {
    void add(Task task) throws ServiceException;

    void edit(Task task) throws ServiceException;

    void delete(int id) throws ServiceException;

    List<Task> getByParams(Map<String, Object> params, int limit, int offset) throws ServiceException;
}
