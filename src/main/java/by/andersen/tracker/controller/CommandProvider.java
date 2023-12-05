package by.andersen.tracker.controller;
import by.andersen.tracker.controller.commandImpl.BasePage;

import java.util.HashMap;
import java.util.Stack;

public class CommandProvider {

    public HashMap <String, Command> commands = new HashMap<>();

    private static String BASE_PAGE = "BasePage";

    public CommandProvider() {
        commands.put(BASE_PAGE, new BasePage());
    }

    public Command getCommand(String name) {

        return commands.getOrDefault(name, (request,response)->{commands.get(BASE_PAGE);});
    }
}
