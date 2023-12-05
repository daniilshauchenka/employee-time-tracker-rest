package by.andersen.tracker.dao;

import by.andersen.tracker.model.Time;
import by.andersen.tracker.dao.exception.DaoException;

public interface ITimeDao {
    void addTime(Time time) throws DaoException;

    void editTime(Time time) throws DaoException;

    void deleteTime(int id) throws DaoException;

    Time getById(int id) throws DaoException;
}
