package by.andersen.tracker.controller.commandImpl;

import by.andersen.tracker.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BasePage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("bpc");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/basepage.jsp");
        requestDispatcher.forward(request,response);
    }
}