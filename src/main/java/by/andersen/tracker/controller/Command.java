package by.andersen.tracker.controller;


import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface Command {

    void execute(HttpServletRequest request, HttpServletResponse response) throws IOException;

    default void handleError(HttpServletResponse response, Map<String, Object> data, int status, Exception ex) throws IOException {
        ex.printStackTrace(); //todo remove print
        data.put("success", false);
        data.put("error", ex.getClass() + " | Caused by: " + ex.getCause() + " | Message: " + ex.getMessage());
        response.setStatus(status);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(data);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonData);
    }

    //checks if this command is available for this method
    default boolean isAllowedFor(HttpMethod method) {
        return method == HttpMethod.DELETE |
                method == HttpMethod.PUT |
                method == HttpMethod.POST |
                method == HttpMethod.GET;
    }

    default void writeResponseData(HttpServletResponse response, Map<String, Object> data, int code) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(data);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        //response.setHeader("Access-Control-Allow-Headers", "");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonData);
        response.setStatus(code);
    }
//  todo need to discuss such solution (using only one ObjectMapper for whole application)

//    default void sendResponse(HttpServletResponse response, Map<String, Object> data, int status) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonData = objectMapper.writeValueAsString(data);
//        response.getWriter().write(jsonData);
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.setStatus(status);
//    }


}
