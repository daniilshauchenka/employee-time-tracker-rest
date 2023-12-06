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
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        provider.getCommand(request.getParameter("command")).execute(request, response);
    }
}