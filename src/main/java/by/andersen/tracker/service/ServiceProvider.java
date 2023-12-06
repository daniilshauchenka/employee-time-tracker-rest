package by.andersen.tracker.service;

import by.andersen.tracker.service.impl.EmployeeServiceImpl;
import by.andersen.tracker.service.impl.TaskServiceImpl;
import by.andersen.tracker.service.impl.TimeServiceImpl;

public final class ServiceProvider {

    private static final ServiceProvider instance = new ServiceProvider();

    private ServiceProvider() {
    }

    private final IEmployeeService employeeService = new EmployeeServiceImpl();

    private final ITaskService taskService = new TaskServiceImpl();

    private final ITimeService timeService = new TimeServiceImpl();

    public IEmployeeService getEmployeeService() {
        return employeeService;
    }

    public ITaskService getTaskService() {
        return taskService;
    }

    public ITimeService getTimeService() {
        return timeService;
    }

    public static ServiceProvider getInstance() {
        return instance;
    }

}
