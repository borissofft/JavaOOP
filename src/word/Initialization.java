package word;

public class Initialization { // new implement

    public static CommandInterface buildCommandInterface(StringBuilder text) {
        CommandInterface commandInterface = new AdvancedCommands(text);
        commandInterface.init();
        return commandInterface;
    }

}
