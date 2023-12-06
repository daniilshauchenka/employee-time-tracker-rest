package by.andersen.tracker.service;

import by.andersen.tracker.model.Time;
import by.andersen.tracker.service.exception.ServiceException;

import java.util.List;

public interface ITimeService {
    void add(Time time) throws ServiceException;

    void edit(Time time) throws ServiceException;

    void delete(int id) throws ServiceException;

    Time getById(int id) throws ServiceException;

    List<Time> getList(int limit, int offset) throws ServiceException;
}
