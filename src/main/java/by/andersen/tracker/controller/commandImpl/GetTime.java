package by.andersen.tracker.controller.commandImpl;

import by.andersen.tracker.controller.Command;
import by.andersen.tracker.controller.HttpMethod;
import by.andersen.tracker.service.ITimeService;
import by.andersen.tracker.service.ServiceProvider;
import by.andersen.tracker.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetTime implements Command {

    private final ITimeService timeService = ServiceProvider.getInstance().getTimeService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("message", "this is get  time!");

        Integer id = getIdFromPath(request.getPathInfo());

        if (id == null || id < 0) {
            data.put("message", "this is get time list!");
            getTimeList(response, data);
        } else {
            data.put("message", "this is get single time!");
            getSingleTime(response, data, id);
        }
        data.put("success", true);
        writeResponseData(response, data, HttpServletResponse.SC_OK);
    }

    private Integer getIdFromPath(String pathInfo) {
        Integer id = null;
        System.out.println("get id from path" + pathInfo);
        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length >= 3) {
                try {
                    id = Integer.parseInt(pathParts[2]);
                } catch (NumberFormatException ignored) {

                }
            }
        }
        return id;
    }

    private void getTimeList(HttpServletResponse response, Map<String, Object> data) throws IOException {
        try {
            data.put("timeList", timeService.getList(1000, 0));
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

    @Override
    public boolean isAllowedFor(HttpMethod method) {
        return method == HttpMethod.GET;
    }
}
