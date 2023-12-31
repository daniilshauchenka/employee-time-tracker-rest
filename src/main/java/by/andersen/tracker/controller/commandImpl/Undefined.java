package by.andersen.tracker.controller.commandImpl;


import by.andersen.tracker.controller.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Undefined implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("undefined");
        Map<String, Object> data = new HashMap<>();
        data.put("message", "command not found!");
        writeResponseData(response,data,400);
    }
}