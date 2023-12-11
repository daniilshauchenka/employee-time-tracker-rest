package by.andersen.tracker.service;

import by.andersen.tracker.model.Time;
import by.andersen.tracker.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface ITimeService {
    void add(Time time) throws ServiceException;

    void edit(Time time) throws ServiceException;

    void delete(int id) throws ServiceException;

    List<Time> getByParams(Map<String, Object> params, int limit, int offset) throws ServiceException;
}
