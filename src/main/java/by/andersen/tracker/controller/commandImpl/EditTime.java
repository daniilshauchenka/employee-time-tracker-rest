package by.andersen.tracker.controller.commandImpl;

import by.andersen.tracker.controller.Command;
import by.andersen.tracker.controller.HttpMethod;
import by.andersen.tracker.model.Time;
import by.andersen.tracker.service.ITimeService;
import by.andersen.tracker.service.ServiceProvider;
import by.andersen.tracker.service.exception.ServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EditTime implements Command {

    private final ITimeService timeService = ServiceProvider.getInstance().getTimeService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("message", "time editing");
        ObjectMapper mapper = new ObjectMapper();
        Time time = mapper.readValue(request.getInputStream(), Time.class);

        try {
            timeService.edit(time);
        } catch (ServiceException ex) {
            handleError(response, data, 500, ex);
            return;
        }

        data.put("success", true);
        String jsonData = mapper.writeValueAsString(data);
        response.getWriter().write(jsonData);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public boolean isAllowedFor(HttpMethod method) {
        return method == HttpMethod.PUT;
    }
}
