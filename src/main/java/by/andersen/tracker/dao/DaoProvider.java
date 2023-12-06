package by.andersen.tracker.dao;

import by.andersen.tracker.dao.IEmployeeDao;
import by.andersen.tracker.dao.ITaskDao;
import by.andersen.tracker.dao.ITimeDao;
import by.andersen.tracker.dao.impl.EmployeeDaoImpl;
import by.andersen.tracker.dao.impl.TaskDaoImpl;
import by.andersen.tracker.dao.impl.TimeDaoImpl;

public class DaoProvider {
    private static final DaoProvider instance = new DaoProvider();

    private DaoProvider() {
    }

    private final IEmployeeDao employeeDao = new EmployeeDaoImpl();
    private final ITaskDao taskDao = new TaskDaoImpl();
    private final ITimeDao timeDao = new TimeDaoImpl();

    public ITaskDao getTaskDao() {
        return taskDao;
    }

    public ITimeDao getTimeDao() {
        return timeDao;
    }

    public IEmployeeDao getEmployeeDao() {
        return employeeDao;
    }


    public static DaoProvider getInstance() {
        return instance;
    }

}
