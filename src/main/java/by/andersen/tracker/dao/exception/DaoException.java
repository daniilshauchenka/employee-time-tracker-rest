package by.andersen.tracker.dao.exception;

public class DaoException extends Exception{
    public DaoException(String e) {
        super(e);
    }

    public DaoException(Exception e) {
        super(e);
    }
}
