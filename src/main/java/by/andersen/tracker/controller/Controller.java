package by.andersen.tracker.controller;

import by.andersen.tracker.controller.commandImpl.Options;
import by.andersen.tracker.controller.commandImpl.Undefined;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/*")
public class Controller extends HttpServlet {

    private final CommandProvider provider = new CommandProvider();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response, HttpMethod.POST);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response, HttpMethod.PUT);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response, HttpMethod.DELETE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response, HttpMethod.GET);
    }

    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response, HttpMethod.OPTIONS);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response, HttpMethod httpMethod) throws IOException {
        String pathInfo = request.getPathInfo();
        System.out.println(pathInfo);
        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");
            System.out.println(pathParts.length);
            if (pathParts.length >= 1) {
                String entityName = pathParts[1];
                System.out.println(httpMethod+" entity name " + entityName);
                Command command = getCommandForMethod(entityName, httpMethod);
                command.execute(request, response);
            }
        }
    }

    private Command getCommandForMethod(String entityName, HttpMethod httpMethod) {

        return switch (httpMethod) {
            case POST -> provider.postCommand(entityName);
            case PUT -> provider.putCommand(entityName);
            case DELETE -> provider.deleteCommand(entityName);
            case GET -> provider.getCommand(entityName);
            case OPTIONS -> new Options();
            default -> new Undefined();
        };
    }
}
