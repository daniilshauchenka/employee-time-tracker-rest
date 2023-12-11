package by.andersen.tracker.controller.commandImpl;

import by.andersen.tracker.controller.Command;
import by.andersen.tracker.controller.HttpMethod;
import by.andersen.tracker.service.ITimeService;
import by.andersen.tracker.service.ServiceProvider;
import by.andersen.tracker.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetTime implements Command {

    private final ITimeService timeService = ServiceProvider.getInstance().getTimeService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("message", "This is get time!");

        Map<String, Object> params = extractParamsFromRequest(request);
        int limit = getParameterOrDefault(request, "limit", 1000);
        int offset = getParameterOrDefault(request, "offset", 0);

        data.put("message", "This is get time list!");
        getTimeList(response, data, params, limit, offset);


     //   Integer id = getIdFromPath(request.getPathInfo());

//        if (id == null || id < 0) {
//            Map<String, Object> params = extractParamsFromRequest(request);
//            int limit = getParameterOrDefault(request, "limit", 1000);
//            int offset = getParameterOrDefault(request, "offset", 0);
//
//            data.put("message", "This is get time list!");
//            getTimeList(response, data, params, limit, offset);
//        } else {
//            data.put("message", "This is get single time!");
//            getSingleTime(response, data, id);
//        }
        data.put("success", true);
        writeResponseData(response, data, HttpServletResponse.SC_OK);
    }

//    private Integer getIdFromPath(String pathInfo) {
//        Integer id = null;
//        System.out.println("get id from path" + pathInfo);
//        if (pathInfo != null) {
//            String[] pathParts = pathInfo.split("/");
//            if (pathParts.length >= 3) {
//                try {
//                    id = Integer.parseInt(pathParts[2]);
//                } catch (NumberFormatException ignored) {
//
//                }
//            }
//        }
//        return id;
//    }

    private void getTimeList(HttpServletResponse response, Map<String, Object> data, Map<String, Object> params, int limit, int offset ) throws IOException {
        try {
            System.out.println(params + " " + limit + " " + offset);
            data.put("timeList", timeService.getListWithParams(params,limit,offset));
        } catch (ServiceException ex) {
            handleError(response, data, 500, ex);
        }
    }

    private void getSingleTime(HttpServletResponse response, Map<String, Object> data, int id) throws IOException {
        try {
            data.put("time", timeService.getById(id));
        } catch (ServiceException ex) {
            handleError(response, data, 500, ex);
        }
    }

    private Map<String, Object> extractParamsFromRequest(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<>();
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.err.println(Arrays.toString(parameterMap.entrySet().toArray()));
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();

            System.out.println("\n\n\n"+ key + " " + Arrays.toString(values) + "\n\n\n");

            if (values.length > 1) {
                List<String> valueList = Arrays.asList(values);
                params.put(key, valueList);
            } else {

                params.put(key, values[0]);
            }
        }

        return params;
    }


    private int getParameterOrDefault(HttpServletRequest request, String paramName, int defaultValue) {
        String paramValue = request.getParameter(paramName);
        if (paramValue != null && !paramValue.isEmpty()) {
            try {
                return Integer.parseInt(paramValue);
            } catch (NumberFormatException ignored) {
                // Можно добавить логгирование или обработку ошибки здесь, если не удалось распарсить значение
            }
        }
        return defaultValue;
    }

    private boolean shouldIgnoreParameter(String parameterName) {

        return parameterName.equals("ignoreParameter");
    }

    @Override
    public boolean isAllowedFor(HttpMethod method) {
        return method == HttpMethod.GET;
    }
}
