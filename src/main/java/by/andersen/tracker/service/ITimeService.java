package by.andersen.tracker.service;

import by.andersen.tracker.model.Time;
import by.andersen.tracker.service.exception.ServiceException;

public interface ITimeService {
    void addTime(Time time) throws ServiceException;

    void editTime(Time time) throws ServiceException;

    void deleteTime(int id) throws ServiceException;

    Time getById(int id) throws ServiceException;
}
