package by.andersen.tracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "controller", value = "/controller")
public class RestController extends HttpServlet {

    private final CommandProvider provider = new CommandProvider();

    //todo
    //idk if that is okay to use one object mapper for whole application and pass it through all commands
    //  private ObjectMapper objectMapper;
//    @Override
//    public void init() throws ServletException {
//        objectMapper = new ObjectMapper();
//    }
//
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        processRequest(request, response, HttpMethod.GET);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response, HttpMethod.POST);
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response, HttpMethod.PUT);
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response, HttpMethod.DELETE);
    }

    //universal method for every coming http method
    private void processRequest(HttpServletRequest request, HttpServletResponse response, HttpMethod method) throws IOException {
        provider.getCommand(request.getParameter("command"), method).execute(request, response);
    }
}