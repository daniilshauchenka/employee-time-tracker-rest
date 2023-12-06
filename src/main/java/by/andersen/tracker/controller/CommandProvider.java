package by.andersen.tracker.controller;

import by.andersen.tracker.controller.commandImpl.*;

import java.util.HashMap;

public class CommandProvider {

    public HashMap<String, Command> commands = new HashMap<>();
    //used by default
    private final static String UNDEFINED = "";

    //Employee request parameters
    private final static String EMPLOYEE_LIST = "employeeList";
    private final static String EMPLOYEE = "employee";
    private final static String EMPLOYEE_ADD = "addEmployee";
    private final static String EMPLOYEE_EDIT = "editEmployee";
    private final static String EMPLOYEE_DELETE = "deleteEmployee";

    //Task request parameters
    private final static String TASK_LIST = "taskList";
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

        commands.put(EMPLOYEE_LIST, new GetEmployeesList());
        commands.put(EMPLOYEE, new GetSingleEmployee());
        commands.put(EMPLOYEE_ADD, new AddEmployee());
        commands.put(EMPLOYEE_EDIT, new EditEmployee());
        commands.put(EMPLOYEE_DELETE, new DeleteEmployee());

        commands.put(TASK_LIST, new GetTaskList());
        commands.put(TASK, new GetSingleTask());
        commands.put(TASK_ADD, new AddTask());
        commands.put(TASK_EDIT, new EditTask());
        commands.put(TASK_DELETE, new DeleteTask());

        commands.put(TIME_LIST, new GetTimeList());
        commands.put(TIME, new GetSingleTime());
        commands.put(TIME_ADD, new AddTime());
        commands.put(TIME_EDIT, new EditTime());
        commands.put(TIME_DELETE, new DeleteTime());
    }

    public Command getCommand(String name, HttpMethod method) {
        System.out.println("getting command" );
        Command command = commands.getOrDefault(name, (request, response) -> commands.get(UNDEFINED));
        System.out.println("got command " + command );
        return command.isAllowedFor(method) ? command : commands.get(UNDEFINED);
    }

}
