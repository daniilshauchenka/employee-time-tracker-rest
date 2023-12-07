package by.andersen.tracker.controller;

import by.andersen.tracker.controller.commandImpl.Undefined;
import by.andersen.tracker.controller.commandImpl.*;

import java.util.HashMap;

public class CommandProvider2 {
    public HashMap<String, Command> getCommands = new HashMap<>();
    public HashMap<String, Command> postCommands = new HashMap<>();
    public HashMap<String, Command> putCommands = new HashMap<>();
    public HashMap<String, Command> deleteCommands = new HashMap<>();
    //used by default
//    private final static String UNDEFINED = "";
//
//    //Employee request parameters
//    private final static String EMPLOYEE_LIST = "employeeList";
//    private final static String EMPLOYEE = "employee";
//    private final static String EMPLOYEE_ADD = "addEmployee";
//    private final static String EMPLOYEE_EDIT = "editEmployee";
//    private final static String EMPLOYEE_DELETE = "deleteEmployee";
//
//    //Task request parameters
//    private final static String TASK_LIST = "taskList";
//    private final static String TASK = "task";
//    private final static String TASK_ADD = "addTask";
//    private final static String TASK_EDIT = "editTask";
//    private final static String TASK_DELETE = "deleteTask";
//
//    //Time request parameters
//    private final static String TIME_LIST = "timeList";
//    private final static String TIME = "time";
//    private final static String TIME_ADD = "addTime";
//    private final static String TIME_EDIT = "editTime";
//    private final static String TIME_DELETE = "deleteTime";


    private final static String EMPLOYEE = "employee";
    private final static String TASK = "task";
    private final static String TIME = "time";


    public CommandProvider2() {
        getCommands.put(EMPLOYEE, new GetEmployee());
        postCommands.put(EMPLOYEE, new PostEmployee());
        putCommands.put(EMPLOYEE, new PutEmployee());
        deleteCommands.put(EMPLOYEE, new DeleteEmployee());

        getCommands.put(TASK, new GetTask());
        postCommands.put(TASK, new PostTask());
        putCommands.put(TASK, new PutTask());
        deleteCommands.put(TASK, new DeleteTask());

        getCommands.put(TIME, new GetTime());
        postCommands.put(TIME, new PostTime());
        putCommands.put(TIME, new PutTime());
        deleteCommands.put(TIME, new DeleteTime());

    }

    public Command getCommand(String command) {
        return getCommands.getOrDefault(command, ((request, response) -> new Undefined()));
    }

    public Command postCommand(String command) {
        return postCommands.getOrDefault(command, ((request, response) -> new Undefined()));
    }

    public Command putCommand(String command) {
        return putCommands.getOrDefault(command, ((request, response) -> new Undefined()));
    }

    public Command deleteCommand(String command) {
        return deleteCommands.getOrDefault(command, ((request, response) -> new Undefined()));
    }


//    public CommandProvider2() {
//
//        employeeCommands.put(HttpMethod.GET, new GetEmployee());
//        employeeCommands.put(HttpMethod.PUT, new PutEmployee());
//        employeeCommands.put(HttpMethod.POST, new PutEmployee());
//        employeeCommands.put(HttpMethod.DELETE, new DeleteEmployee());

//        taskCommands.put(TASK_LIST, new GetTaskList());
//        taskCommands.put(TASK, new GetSingleTask());
//        taskCommands.put(TASK_ADD, new AddTask());
//        taskCommands.put(TASK_EDIT, new EditTask());
//        taskCommands.put(TASK_DELETE, new DeleteTask());
//
//        timeCommands.put(TIME_LIST, new GetTimeList());
//        timeCommands.put(TIME, new GetSingleTime());
//        timeCommands.put(TIME_ADD, new AddTime());
//        timeCommands.put(TIME_EDIT, new EditTime());
//        timeCommands.put(TIME_DELETE, new DeleteTime());
//    }

//    public Command fetchCommand(String entityName) {
//        System.out.println(entityName );
//        HashMap<HttpMethod, Command> selectedCommands = commandMap.getOrDefault(entityName, new HashMap<>());
//        System.out.println("selecting command");
//        Command command = selectedCommands.getOrDefault(method, (request, response) -> new Undefined());
//
//        return command;


//        System.out.println("getting command" );
//        Command command = commands.getOrDefault(method, (request, response) -> commands.get(UNDEFINED));
//        System.out.println("got command " + command );
//        return command.isAllowedFor(method) ? command : commands.get(UNDEFINED);
//    }

}
