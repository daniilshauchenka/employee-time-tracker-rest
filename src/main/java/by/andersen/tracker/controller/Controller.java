package by.andersen.tracker.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/*")
public class Controller extends HttpServlet {

    private final CommandProvider2 provider = new CommandProvider2();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest2(request, response, HttpMethod.POST);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest2(request, response, HttpMethod.PUT);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest2(request, response, HttpMethod.DELETE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest2(request, response, HttpMethod.GET);
    }


    private void handleRequest2(HttpServletRequest request, HttpServletResponse response, HttpMethod httpMethod) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        System.out.println(pathInfo);
        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");
            System.out.println(pathParts.length);
            if (pathParts.length >= 1) {
                String entityName = pathParts[1];
                System.out.println("entity name =====" + entityName);
                Command command = getCommandForMethod(entityName, httpMethod);
                command.execute(request, response);
            }
        }
    }

    private Command getCommandForMethod(String entityName, HttpMethod httpMethod) {
        System.out.println(entityName + " " + httpMethod);
        switch (httpMethod) {
            case POST:
                return provider.postCommand(entityName);
            case PUT:
                return provider.putCommand(entityName);
            case DELETE:
                return provider.deleteCommand(entityName);
            default:
                return provider.getCommand(entityName);
        }
    }

        private void handleRequest(HttpServletRequest request, HttpServletResponse response, HttpMethod httpMethod) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        System.out.println(pathInfo);
        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");
            System.out.println(pathParts.length);
            if (pathParts.length >= 2) {
                String entityName = pathParts[1];

                Command command = provider.getCommand(entityName);
                command.execute(request, response);
            }
        }
    }
}
