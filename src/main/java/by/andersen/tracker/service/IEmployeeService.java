package by.andersen.tracker.service;

import by.andersen.tracker.model.Employee;
import by.andersen.tracker.model.Time;
import by.andersen.tracker.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface IEmployeeService {
    void add(Employee employee) throws ServiceException;

    void edit(Employee employee) throws ServiceException;

    void delete(int id) throws ServiceException;

    List<Employee> getByParams(Map<String, Object> params, int limit, int offset) throws ServiceException;}
