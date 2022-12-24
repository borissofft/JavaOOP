package L12DesignPatterns.Lab.command;

public class LightOff implements Command { // Команда

    private Lamp lamp;

    public LightOff(Lamp lamp) {
        this.lamp = lamp;
    }

    @Override
    public void execute() {
        this.lamp.off();
    }

}
