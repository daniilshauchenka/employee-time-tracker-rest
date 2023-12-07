package by.andersen.tracker.controller.commandImpl;


import by.andersen.tracker.controller.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Options implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("OPTIONS");
        Map<String, Object> data = new HashMap<>();
        data.put("message", "hello options!");
        writeResponseData(response,data,200);
    }
}