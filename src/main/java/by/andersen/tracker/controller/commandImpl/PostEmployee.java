package by.andersen.tracker.controller.commandImpl;

import by.andersen.tracker.controller.Command;
import by.andersen.tracker.controller.HttpMethod;
import by.andersen.tracker.model.Employee;
import by.andersen.tracker.service.IEmployeeService;
import by.andersen.tracker.service.ServiceProvider;
import by.andersen.tracker.service.exception.ServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PostEmployee implements Command {
    private final IEmployeeService employeeService = ServiceProvider.getInstance().getEmployeeService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("message", "this is add employee!");
        ObjectMapper objectMapper = new ObjectMapper();
        Employee employee = objectMapper.readValue(request.getInputStream(), Employee.class);
        try {
            employeeService.add(employee);
        } catch (ServiceException ex) {
            handleError(response, data, 500, ex);
            return;
        }
        data.put("success", true);
        writeResponseData(response, data, HttpServletResponse.SC_OK);
    }

    @Override
    public boolean isAllowedFor(HttpMethod method) {
        return method == HttpMethod.POST;
    }
}
