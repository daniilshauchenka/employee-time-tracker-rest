package by.andersen.tracker.controller;

import by.andersen.tracker.controller.commandImpl.GetEmployeesList;
import by.andersen.tracker.controller.commandImpl.Undefined;

import java.util.HashMap;

public class CommandProvider {

    public HashMap<String, Command> commands = new HashMap<>();
    private static String UNDEFINED = "undefined";

    private static String EMPLOYEES_LIST = "employeesList";
    private static String EMPLOYEE = "employee";
    private static String EMPLOYEE_ADD = "addEmployee";
    private static String EMPLOYEE_EDIT = "editEmployee";
    private static String EMPLOYEE_DELETE = "deleteEmployee";


    public CommandProvider() {
        commands.put(EMPLOYEES_LIST, new GetEmployeesList());
        commands.put(UNDEFINED, new Undefined());

    }

    public Command getCommand(String name) {
        return commands.getOrDefault(name, (request, response) -> {
            commands.get(UNDEFINED).execute(request, response);
        });
    }
}
