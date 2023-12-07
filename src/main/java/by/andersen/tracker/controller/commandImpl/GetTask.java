package by.andersen.tracker.controller.commandImpl;

import by.andersen.tracker.controller.Command;
import by.andersen.tracker.controller.HttpMethod;
import by.andersen.tracker.service.ITaskService;
import by.andersen.tracker.service.ServiceProvider;
import by.andersen.tracker.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetTask implements Command {
    private final ITaskService taskService = ServiceProvider.getInstance().getTaskService();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("message", "this is get single task!");

        Integer id = getIdFromPath(request.getPathInfo());

        if (id == null || id < 0) {
            getTaskList(response, data);
        } else {
            getSingleTask(response, data, id);
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

    private void getTaskList(HttpServletResponse response, Map<String, Object> data) throws IOException {
        try {
            data.put("taskList", taskService.getList(1000, 0));
        } catch (ServiceException ex) {
            handleError(response, data, 500, ex);
        }
    }

    private void getSingleTask(HttpServletResponse response, Map<String, Object> data, int id) throws IOException {
        try {
            data.put("task", taskService.getById(id));
        } catch (ServiceException ex) {
            handleError(response, data, 500, ex);
        }
    }


    @Override
    public boolean isAllowedFor(HttpMethod method) {
        return method == HttpMethod.GET;
    }
}
