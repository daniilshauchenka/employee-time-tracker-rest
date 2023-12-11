package by.andersen.tracker.controller.commandImpl;

import by.andersen.tracker.controller.Command;
import by.andersen.tracker.controller.HttpMethod;
import by.andersen.tracker.service.IEmployeeService;
import by.andersen.tracker.service.ServiceProvider;
import by.andersen.tracker.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetEmployee implements Command {
    private final IEmployeeService employeeService = ServiceProvider.getInstance().getEmployeeService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("message", "this is get employee!");

        Map<String, Object> params = extractParamsFromRequest(request);
        int limit = getParameterOrDefault(request, "limit", 1000);
        int offset = getParameterOrDefault(request, "offset", 0);
        getEmployeeList(response, data, params, limit, offset);

        data.put("success", true);
        writeResponseData(response, data, HttpServletResponse.SC_OK);
    }

    private Map<String, Object> extractParamsFromRequest(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<>();
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.err.println(Arrays.toString(parameterMap.entrySet().toArray()));
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();

            if (values.length > 1) {
                List<String> valueList = Arrays.asList(values);
                params.put(key, valueList);
            } else {
                params.put(key, values[0]);
            }
        }

        return params;
    }

    private void getEmployeeList(HttpServletResponse response, Map<String, Object> data, Map<String, Object> params, int limit, int offset ) throws IOException {
        try {
            System.out.println(params + " " + limit + " " + offset);
            data.put("employeeList", employeeService.getByParams(params,limit,offset));
        } catch (ServiceException ex) {
            handleError(response, data, 500, ex);
        }
    }

    private int getParameterOrDefault(HttpServletRequest request, String paramName, int defaultValue) {
        String paramValue = request.getParameter(paramName);
        if (paramValue != null && !paramValue.isEmpty()) {
            try {
                return Integer.parseInt(paramValue);
            } catch (NumberFormatException ignored) {
            }
        }
        return defaultValue;
    }

    @Override
    public boolean isAllowedFor(HttpMethod method) {
        return method == HttpMethod.GET;
    }
}
