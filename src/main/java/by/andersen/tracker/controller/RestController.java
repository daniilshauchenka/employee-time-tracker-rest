package by.andersen.tracker.controller;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "controller", value = "/controller")
public class RestController extends HttpServlet {

    private final CommandProvider provider = new CommandProvider();

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        provider.getCommand(request.getParameter("command")).execute(request, response);

    }

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public void doPut (HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
    public void doDelete (HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public void destroy() {
    }


}