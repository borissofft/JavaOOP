package L12DesignPatterns.Lab.command;

import java.util.ArrayDeque;

public class Switch { // Invoker - извиква командите, в зависимост коя команда ще му се подаде

    private ArrayDeque<Command> history;

    public Switch() {
        this.history = new ArrayDeque<>();
    }

    public void storeAndExecute(Command command) {
        this.history.push(command);
        command.execute();
    }

    public void undoExecuted() { // можем да връщаме командите, след като сме ги запазили в стека history
        this.history.pop();     // премахваме последната команда и в стека остава предпоследната
        Command command = this.history.peek();
        if (command != null) {
            command.execute();
        }
    }

}
