package by.andersen.tracker.controller;

import by.andersen.tracker.controller.commandImpl.Undefined;
import by.andersen.tracker.controller.commandImpl.*;

import java.util.HashMap;

public class CommandProvider {
    public HashMap<String, Command> getCommands = new HashMap<>();
    public HashMap<String, Command> postCommands = new HashMap<>();
    public HashMap<String, Command> putCommands = new HashMap<>();
    public HashMap<String, Command> deleteCommands = new HashMap<>();

    private final static String EMPLOYEE = "employee";
    private final static String TASK = "task";
    private final static String TIME = "time";

    public CommandProvider() {
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



}
