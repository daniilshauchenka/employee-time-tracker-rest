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

public class DeleteTask implements Command {

    private final ITaskService taskService = ServiceProvider.getInstance().getTaskService();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("message", "this is delete task!");
        int id=0;
        try{
            id = Integer.parseInt(request.getParameter("id"));

        } catch(NumberFormatException ex) {
            handleError(response, data, 500, ex);
        }

        try {
            taskService.delete(id);
        } catch (ServiceException ex) {
            handleError(response, data, 500, ex);
        }
        data.put("success", true);
        writeResponseData(response, data, HttpServletResponse.SC_OK);
    }

    @Override
    public boolean isAllowedFor(HttpMethod method) {
        return method == HttpMethod.DELETE;
    }
}
