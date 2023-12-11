package by.andersen.tracker.service.impl;

import by.andersen.tracker.dao.DaoProvider;
import by.andersen.tracker.dao.ITimeDao;
import by.andersen.tracker.dao.exception.DaoException;
import by.andersen.tracker.model.Time;
import by.andersen.tracker.service.ITimeService;
import by.andersen.tracker.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public class TimeServiceImpl implements ITimeService {

    ITimeDao timeDao = DaoProvider.getInstance().getTimeDao();

    @Override
    public void add(Time time) throws ServiceException {
        try {
            timeDao.add(time);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void edit(Time time) throws ServiceException {
        try {
            timeDao.add(time);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            timeDao.delete(id);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Time getById(int id) throws ServiceException {
        try {
            return timeDao.getById(id);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<Time> getList(int limit, int offset) throws ServiceException {
        try {
            return timeDao.getList(limit, offset);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }
    @Override
    public List<Time> getListWithParams(Map<String, Object> params, int limit, int offset) throws ServiceException {
        try {
            return timeDao.getListWithParams(params,limit, offset);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

}
