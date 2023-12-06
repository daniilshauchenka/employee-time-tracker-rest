package by.andersen.tracker.controller;

import by.andersen.tracker.controller.commandImpl.*;

import java.util.HashMap;

public class CommandProvider {

    public HashMap<String, Command> commands = new HashMap<>();
    //used by default
    private final static String UNDEFINED = "";

    //Employee request parameters
    private final static String EMPLOYEES_LIST = "employeesList";
    private final static String EMPLOYEE = "employee";
    private final static String EMPLOYEE_ADD = "addEmployee";
    private final static String EMPLOYEE_EDIT = "editEmployee";
    private final static String EMPLOYEE_DELETE = "deleteEmployee";

    //Task request parameters
    private final static String TASKS_LIST = "tasksList";
    private final static String TASK = "task";
    private final static String TASK_ADD = "addTask";
    private final static String TASK_EDIT = "editTask";
    private final static String TASK_DELETE = "deleteTask";

    //Time request parameters
    private final static String TIME_LIST = "timeList";
    private final static String TIME = "time";
    private final static String TIME_ADD = "addTime";
    private final static String TIME_EDIT = "editTime";
    private final static String TIME_DELETE = "deleteTime";


    public CommandProvider() {
        commands.put(UNDEFINED, new Undefined());

        commands.put(EMPLOYEES_LIST, new GetEmployeesList());
        commands.put(EMPLOYEE, new GetSingleEmployee());
        commands.put(EMPLOYEE_ADD, new AddEmployee());
        commands.put(EMPLOYEE_EDIT, new EditEmployee());
        commands.put(EMPLOYEE_DELETE, new DeleteEmployee());

        //todo create classes for each command, do the implementation
        commands.put(TASKS_LIST, new Undefined());
        commands.put(TASK, new Undefined());
        commands.put(TASK_ADD, new Undefined());
        commands.put(TASK_EDIT, new Undefined());
        commands.put(TASK_DELETE, new Undefined());

        commands.put(TIME_LIST, new Undefined());
        commands.put(TIME, new Undefined());
        commands.put(TIME_ADD, new Undefined());
        commands.put(TIME_EDIT, new Undefined());
        commands.put(TIME_DELETE, new Undefined());
    }

    public Command getCommand(String name, HttpMethod method) {
        System.out.println("getting command" );
        Command command = commands.getOrDefault(name, (request, response) -> commands.get(UNDEFINED));
        System.out.println("got command " + command );
        return command.isAllowedFor(method) ? command : commands.get(UNDEFINED);

//        if (command.isAllowedFor(method)) {
//            return command;
//        }
//        else {
//          return  commands.get(UNDEFINED);
//        }
    }

}
